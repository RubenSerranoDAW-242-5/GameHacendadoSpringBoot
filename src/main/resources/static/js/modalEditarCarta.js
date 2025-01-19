function openModal(element) {
    const idCarta = element.getAttribute('data-id');
    fetch(`/zonaCartas/getCarta?id=${idCarta}`)
        .then(response => response.json())
        .then(data => {
            if (data) {
                document.getElementById('editId').value = data.id;
                document.getElementById('editNombre').value = data.nombreCarta;
                document.getElementById('editTipo').value = data.tipoCarta;
                document.getElementById('editCoste').value = data.costeCarta;
                document.getElementById('editColor').value = data.color;
                document.getElementById('editCodigo').value = data.codigoCarta;
                document.getElementById('editPrecio').value = data.precioCarta;
                document.getElementById('editCantidad').value = data.cantidad;

                document.getElementById('editModal').style.display = 'block';
            } else {
                console.error('No se encontraron datos para la carta con id:', idCarta);
            }
        })
        .catch(error => console.error('Error al obtener los datos de la carta:', error));
}

function closeModal() {
    document.getElementById('editModal').style.display = 'none';
}

window.onclick = function(event) {
    if (event.target == document.getElementById('editModal')) {
        closeModal();
    }
}
