document.getElementById('upload-icon').addEventListener('click', function() {
    document.getElementById('file-input').click();
});

document.getElementById('file-input').addEventListener('change', function(event) {
    var files = event.target.files;
    var preview = document.getElementById('image-preview');
    preview.innerHTML = '';

    var processFiles = Array.from(files).slice(0, 3);

    if (files.length > 3) {
        alert('올릴 수 있는 파일은 최대 3개입니다.');
    }

    processFiles.forEach(file => {
        var imgElement = document.createElement('img');
        imgElement.style.width = '50px';
        imgElement.style.height = '50px';
        imgElement.style.marginRight = '10px';

        var reader = new FileReader();
        reader.onload = function(e) {
            imgElement.src = e.target.result;
            preview.appendChild(imgElement);
        };
        reader.readAsDataURL(file);
    });


    document.getElementById('fileUpload-cnt').textContent = `${processFiles.length}/3`;
});
document.querySelectorAll('input[name="auctionCheck"]').forEach(radio => {
    radio.addEventListener('change', function() {
        const priceInput = document.getElementById('price');
        if (this.id === 'auction') {
            priceInput.value = '';
            priceInput.placeholder = '경매 시작 가격';
        } else {
            priceInput.value = '';
            priceInput.placeholder = '상품 가격';
        }
    });
});
const submitButton = document.getElementById('product_submit');

function formSubmitProduct(form){
    submitButton.disabled =true;
    if(!form.title.value.trim()){
        alert("상품명을 입력해주세요");
        submitButton.disabled =false;
        return;
    }if(!form.price.value.trim()){
        alert("상품 가격을 입력해주세요");
        submitButton.disabled =false;
        return;
    }if(!form.info.value.trim()){
        alert("상품 내용을 입력해주세요");
        submitButton.disabled =false;
        return;
    }
    alert("정상적으로 상품이 등록되었습니다.");
    form.submit();
}