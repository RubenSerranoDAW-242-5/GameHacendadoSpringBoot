let selectedCategories = [];

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

function openAddModal() {
    document.getElementById('addModal').style.display = 'block';
}

function closeAddModal() {
    document.getElementById('addModal').style.display = 'none';
}

function previewImage(event) {
    const reader = new FileReader();
    reader.onload = function() {
        const output = document.getElementById('imagePreview');
        output.src = reader.result;
        output.style.display = 'block';
    };
    reader.readAsDataURL(event.target.files[0]);
}

function updateSelectedCategories() {
    const select = document.getElementById('option-selector');
    const selectedOption = select.options[select.selectedIndex];
    const categoryId = selectedOption.value;
    const categoryText = selectedOption.text;

    if (!selectedCategories.includes(categoryId)) {
        selectedCategories.push(categoryId);
        displaySelectedCategories(categoryId, categoryText);
        updateSelectedCategoriesInput();
    }
}

function displaySelectedCategories(categoryId, categoryText) {
    const list = document.getElementById('selectedCategoriesList');
    const li = document.createElement('li');
    li.textContent = categoryText;
    li.setAttribute('data-id', categoryId);
    list.appendChild(li);
}

function updateSelectedCategoriesInput() {
    const input = document.getElementById('selectedCategoriesInput');
    input.value = selectedCategories.join(',');
    console.log(input.value); 6
}


document.getElementById('add-option').addEventListener('click', updateSelectedCategories);

window.onclick = function(event) {
    if (event.target == document.getElementById('editModal')) {
        closeModal();
    }
    if (event.target == document.getElementById('addModal')) {
        closeAddModal();
    }
}
