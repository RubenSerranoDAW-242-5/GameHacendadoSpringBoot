document.addEventListener('DOMContentLoaded', function () {
    console.log("DOM cargado");

    // Buscador de cartas
    const searchInput = document.querySelector('#searchInput');
    const gridContainer = document.getElementById('grid');

    if (searchInput) {
        searchInput.addEventListener('input', function () {
            const query = this.value.trim();
            if (query.length > 0) {
                fetch(`/buscarCartas?query=${query}`)
                    .then(response => response.text())
                    .then(html => {
                        gridContainer.innerHTML = html;
                    })
                    .catch(error => console.error('Error fetching search results:', error));
            } else {
                // Si no hay búsqueda, recargar todas las cartas
                fetch(`/buscarCartas?query=`)
                    .then(response => response.text())
                    .then(html => {
                        gridContainer.innerHTML = html;
                    })
                    .catch(error => console.error('Error fetching default results:', error));
            }
        });
    }

    // Incrementar/Decrementar cantidades
    document.addEventListener('click', function (e) {
        if (e.target.matches('.btn-mas') || e.target.matches('.btn-menos')) {
            const idCarta = e.target.getAttribute('data-id');
            const inputCantidad = document.getElementById(`cantidad-${idCarta}`);
            if (inputCantidad) {
                let cantidadActual = parseInt(inputCantidad.value);
                const cantidadMax = inputCantidad.max;

                if (e.target.matches('.btn-mas') && cantidadActual < cantidadMax) {
                    inputCantidad.value = ++cantidadActual;
                }

                if (e.target.matches('.btn-menos') && cantidadActual > 1) {
                    inputCantidad.value = --cantidadActual;
                }
            }
        }
    });
});
