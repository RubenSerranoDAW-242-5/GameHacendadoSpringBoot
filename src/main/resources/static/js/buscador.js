document.addEventListener('DOMContentLoaded', function () {
    const searchInput = document.querySelector('#searchInput');
    const categorySelect = document.querySelector('select[name="categoria"]');
    const gridContainer = document.getElementById('grid');
    const currentUri = window.location.pathname;

    if (searchInput && categorySelect && gridContainer) {
        function fetchResults() {
            const query = searchInput.value.trim();
            const categoria = categorySelect.value;
            const admin = currentUri === '/zonaCartas';

            console.log(`Buscando con query="${query}", categoria=${categoria}, admin=${admin}`);

            fetch(`/buscar?query=${encodeURIComponent(query)}&categoria=${encodeURIComponent(categoria)}&admin=${admin}`)
                .then(response => {
                    if (!response.ok) {
                        throw new Error(`Error ${response.status}: ${response.statusText}`);
                    }
                    return response.text();
                })
                .then(html => {
                    gridContainer.innerHTML = html;
                    registrarEventos(); // Registrar eventos después de cargar contenido dinámicamente

                    const cartas = gridContainer.querySelectorAll('.carta');
                    cartas.forEach((carta, index) => {
                        carta.style.animationDelay = `${index * 0.1}s`;
                    });
                })
                .catch(error => console.error('Error al buscar resultados:', error));
        }

        searchInput.addEventListener('input', fetchResults);
        categorySelect.addEventListener('change', fetchResults);
    } else {
        console.error('Elementos del DOM no encontrados.');
    }
});
