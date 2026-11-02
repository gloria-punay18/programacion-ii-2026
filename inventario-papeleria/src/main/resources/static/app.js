const API_URL = 'http://localhost:8080/api';
let productosMemoria = [];
let historialMovimientos = [];
let filtroActual = 'TODOS';
let productoIdAEliminar = null;

document.addEventListener('DOMContentLoaded', () => {
    cargarProductos();


    document.getElementById('form-movimiento').addEventListener('submit', async (e) => {
        e.preventDefault();

        const productoId = parseInt(document.getElementById('productoId').value);
        let valorSeleccionado = document.getElementById('tipoMovimiento').value;


        let tipo = 'ENTRADA';
        if (valorSeleccionado.includes('SALIDA')) {
            tipo = 'SALIDA';
        }

        const cantidad = parseInt(document.getElementById('cantidad').value);
        const mensajeDiv = document.getElementById('mensaje-movimiento');


        const movimientoPayload = {
            productoId: productoId,
            cantidad: cantidad,
            tipo: tipo
        };

        try {
            const response = await fetch(`${API_URL}/movimientos`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(movimientoPayload)
            });

            if (response.ok) {
                const data = await response.json();
                mensajeDiv.className = 'mensaje exito';
                mensajeDiv.innerText = `¡Movimiento registrado exitosamente!`;


                registrarHistorial(productoId, tipo, cantidad);

                document.getElementById('form-movimiento').reset();
                cargarProductos();
            } else {
                const data = await response.json();
                let detalleError = data.mensaje || data.message;
                if (data.errors && data.errors.length > 0) {
                    detalleError = data.errors.map(err => err.defaultMessage || err).join(", ");
                }
                mensajeDiv.className = 'mensaje error';
                mensajeDiv.innerText = detalleError || 'Error al procesar movimiento.';
            }
        } catch (error) {
            mensajeDiv.className = 'mensaje error';
            mensajeDiv.innerText = 'Error de conexión con el servidor.';
        }
    });


    document.getElementById('form-producto').addEventListener('submit', async (e) => {
        e.preventDefault();

        const id = document.getElementById('productoIdEditar').value;
        const codigo = document.getElementById('nuevoCodigo').value;
        const nombre = document.getElementById('nuevoNombre').value;
        const precio = parseFloat(document.getElementById('nuevoPrecio').value);
        const stock = parseInt(document.getElementById('nuevoStock').value);
        const stockMinimo = parseInt(document.getElementById('nuevoStockMinimo').value);
        const mensajeDiv = document.getElementById('mensaje-producto');


        const productoPayload = {
            codigo: codigo,
            nombre: nombre,
            descripcion: nombre,
            precio: precio,
            stock: stock,
            stockInicial: stock,
            stockMinimo: stockMinimo,
            categoriaId: 1
        };

        const esEdicion = id !== "";

        const url = esEdicion ? `${API_URL}/productos/${id}` : `${API_URL}/productos`;
        const method = esEdicion ? 'PUT' : 'POST';

        try {
            const response = await fetch(url, {
                method: method,
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(productoPayload)
            });

            if (response.ok) {
                mensajeDiv.className = 'mensaje exito';
                mensajeDiv.innerText = esEdicion ? '¡Producto actualizado!' : '¡Producto guardado exitosamente!';
                cancelarEdicion();
                cargarProductos();
            } else {
                const errData = await response.json();

                let detalleError = errData.mensaje || errData.message;
                if (errData.errors && errData.errors.length > 0) {
                    detalleError = errData.errors.map(err => err.defaultMessage || err).join(", ");
                }

                mensajeDiv.className = 'mensaje error';
                mensajeDiv.innerText = detalleError || 'Error al guardar los datos.';
            }
        } catch (error) {
            mensajeDiv.className = 'mensaje error';
            mensajeDiv.innerText = 'Error de conexión con el servidor.';
        }
    });


    document.getElementById('modal-btn-confirmar').addEventListener('click', ejecutarEliminacion);
});

async function cargarProductos() {
    const tbody = document.getElementById('tabla-productos');
    tbody.innerHTML = '<tr><td colspan="8" style="text-align: center; color: var(--text-secondary);">Cargando inventario...</td></tr>';

    try {
        const response = await fetch(`${API_URL}/productos`);
        productosMemoria = await response.json();

        aplicarFiltrosGlobales();
        actualizarKPIs(productosMemoria);
    } catch (error) {
        tbody.innerHTML = '<tr><td colspan="8" class="mensaje error" style="display: table-cell; text-align: center;">Error de conexión con la API.</td></tr>';
    }
}

function aplicarFiltrosGlobales() {
    const texto = document.getElementById('input-buscar').value.toLowerCase();

    let resultado = productosMemoria.filter(p =>
        p.nombre.toLowerCase().includes(texto) ||
        p.codigo.toLowerCase().includes(texto)
    );

    if (filtroActual === 'NORMAL') {
        resultado = resultado.filter(p => p.stock > p.stockMinimo);
    } else if (filtroActual === 'BAJO') {
        resultado = resultado.filter(p => p.stock <= p.stockMinimo);
    }

    renderizarTabla(resultado);
}

function renderizarTabla(lista) {
    const tbody = document.getElementById('tabla-productos');
    tbody.innerHTML = '';

    if (lista.length === 0) {
        tbody.innerHTML = '<tr><td colspan="8" style="text-align: center; color: var(--text-secondary);">No hay productos que coincidan.</td></tr>';
        return;
    }

    lista.forEach(p => {
        const esStockBajo = p.stock <= p.stockMinimo;
        const valorTotalProd = (p.precio * p.stock).toFixed(2);
        const row = document.createElement('tr');
        if (esStockBajo) row.classList.add('alerta-stock');

        row.innerHTML = `
            <td>${p.id}</td>
            <td><strong style="color: #00f2fe;">${p.codigo}</strong></td>
            <td>${p.nombre}</td>
            <td>Q${parseFloat(p.precio).toFixed(2)}</td>
            <td><strong>${p.stock}</strong> ${esStockBajo ? '<span class="badge">¡Bajo!</span>' : ''}</td>
            <td>${p.stockMinimo}</td>
            <td style="color: #4facfe;">Q${valorTotalProd}</td>
            <td>
                <button class="btn-action edit" onclick="prepararEdicion(${p.id})">Editar</button>
                <button class="btn-action delete" onclick="abrirModalEliminar(${p.id})">Eliminar</button>
            </td>
        `;
        tbody.appendChild(row);
    });
}

function actualizarKPIs(lista) {
    let valorTotal = 0;
    let bajo = 0;

    lista.forEach(p => {
        valorTotal += (p.precio * p.stock);
        if (p.stock <= p.stockMinimo) bajo++;
    });

    document.getElementById('kpi-total-productos').innerText = lista.length;
    document.getElementById('kpi-valor-total').innerText = `Q${valorTotal.toLocaleString('es-GT', { minimumFractionDigits: 2 })}`;
    document.getElementById('kpi-stock-bajo').innerText = bajo;
}

function aplicarFiltro(tipo, btn) {
    filtroActual = tipo;
    document.querySelectorAll('.filter-btn').forEach(b => b.classList.remove('active'));
    btn.classList.add('active');
    aplicarFiltrosGlobales();
}

function filtrarProductos() {
    aplicarFiltrosGlobales();
}


function exportarCSV() {
    if (productosMemoria.length === 0) return alert("No hay datos para exportar.");

    let csv = "ID,Codigo,Nombre,Precio,Stock,StockMinimo,ValorTotal\n";
    productosMemoria.forEach(p => {
        csv += `${p.id},"${p.codigo}","${p.nombre}",${p.precio},${p.stock},${p.stockMinimo},${(p.precio * p.stock).toFixed(2)}\n`;
    });

    const blob = new Blob([csv], { type: 'text/csv' });
    const url = window.URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.href = url;
    a.download = `inventario_papeleria_${new Date().toISOString().slice(0,10)}.csv`;
    a.click();
}


function registrarHistorial(id, tipo, cant) {
    const hora = new Date().toLocaleTimeString();
    historialMovimientos.unshift({ hora, id, tipo, cant });

    const tbody = document.getElementById('tabla-historial');
    tbody.innerHTML = '';
    historialMovimientos.slice(0, 5).forEach(h => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${h.hora}</td>
            <td><strong>#${h.id}</strong></td>
            <td><span class="${h.tipo === 'ENTRADA' ? 'badge-entrada' : 'badge-salida'}">${h.tipo}</span></td>
            <td>${h.cant} unidades</td>
        `;
        tbody.appendChild(row);
    });
}


function abrirModalEliminar(id) {
    productoIdAEliminar = id;
    document.getElementById('modal-mensaje').innerText = `¿Estás segura de eliminar el producto ID #${id}?`;
    document.getElementById('modal-confirm').style.display = 'flex';
}

function cerrarModal() {
    document.getElementById('modal-confirm').style.display = 'none';
    productoIdAEliminar = null;
}

async function ejecutarEliminacion() {
    if (!productoIdAEliminar) return;

    try {
        const response = await fetch(`${API_URL}/productos/${productoIdAEliminar}`, { method: 'DELETE' });
        cerrarModal();

        if (response.ok) {
            cargarProductos();
        } else {
            const data = await response.json();
            alert(data.mensaje || data.message || "No se puede eliminar por restricciones de integridad.");
        }
    } catch (error) {
        cerrarModal();
        alert("Error de conexión al eliminar.");
    }
}

function prepararEdicion(id) {
    const prod = productosMemoria.find(p => p.id === id);
    if (!prod) return;

    document.getElementById('productoIdEditar').value = prod.id;
    document.getElementById('nuevoCodigo').value = prod.codigo;
    document.getElementById('nuevoNombre').value = prod.nombre;
    document.getElementById('nuevoPrecio').value = prod.precio;
    document.getElementById('nuevoStock').value = prod.stock;
    document.getElementById('nuevoStockMinimo').value = prod.stockMinimo;

    document.getElementById('form-producto-titulo').innerText = `Editar Producto #${prod.id}`;
    document.getElementById('btn-texto-producto').innerText = "Actualizar Producto";
    document.getElementById('btn-cancelar-edicion').style.display = "inline-flex";

    window.scrollTo({ top: 300, behavior: 'smooth' });
}

function cancelarEdicion() {
    document.getElementById('form-producto').reset();
    document.getElementById('productoIdEditar').value = "";
    document.getElementById('form-producto-titulo').innerText = "Registrar Nuevo Producto";
    document.getElementById('btn-texto-producto').innerText = "Guardar Producto";
    document.getElementById('btn-cancelar-edicion').style.display = "none";
}