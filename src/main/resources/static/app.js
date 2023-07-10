let loadBooksBtn = document.getElementById('loadBooks')

loadBooksBtn.addEventListener('click', onLoadBooks);
let baseUrl ="http://localhost:8080/api/books";

async  function  onLoadBooks() {
     let authorsContainer = document.getElementById('authors-container')
    authorsContainer.innerHTML = ''

    try {
        const response = await fetch(baseUrl);
        if (!response.ok) {
            throw new Error(response.statusText);
        }
        const books = await response.json();
        Object.entries(books).forEach((obj) => {
            let tr = createTr(obj[0], obj[1],obj[2]);
            authorsContainer.appendChild(tr);
        });
    } catch (error) {
        alert(error);
    }


    console.log(books);




}



function createTr(key, obj) {
    let tr = document.createElement("tr");
    let tdBook = document.createElement("td");
    tdBook.textContent = obj.title;

    let tdAuthor = document.createElement("td");
    tdAuthor.textContent = obj.author.name;
    let tdISBN = document.createElement("td");
    tdISBN.textContent = obj.isbn;
    let tdBtn = document.createElement("td");
    let editBtn = document.createElement("button");
    editBtn.textContent = "Edit";
    editBtn.id = key;
    editBtn.addEventListener("click", edit);
    tdBtn.appendChild(editBtn);
    let deleteBtn = document.createElement("button");
    deleteBtn.textContent = "Delete";
    deleteBtn.id = key;
    deleteBtn.addEventListener("click", deleteRecord);
    tdBtn.appendChild(deleteBtn);
    tr.appendChild(tdBook);
    tr.appendChild(tdISBN);
    tr.appendChild(tdAuthor);
     tr.appendChild(tdBtn);
    return tr;
}

async function save() {
    if (!inputTitle.value || !inputAuthor.value) {
        alert("Please fill all fields");
    } else {
        let method = "";
        let url=""
        if (submitBtn.textContent == "Submit") {
            method = "POST";
            url=baseUrl
        } else if (submitBtn.textContent == "Save") {
            method = "PUT";
            url = baseUrl + "/" + id;
            submitBtn.textContent = "Submit";
        }
        try {
            let response = await fetch(url, {
                method: `${method}`,
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({
                    title: inputTitle.value,
                    author: inputAuthor.value,
                }),
            });
            if (!response.ok) {
                throw new Error(`${response.status} ${response.statusText}`);
            }
            h3.textContent = "FORM";
            //inputTitle.value = "";
            //  inputAuthor.value = "";
            loadAll();
            return await response.json;
        } catch(error) {
            alert(error);
        }
    }
}

function edit(ev) {
    submitBtn.textContent = "Save";
    id = ev.target.id;
    h3.textContent = "Edit FORM";
    inputTitle.value =
        ev.target.parentElement.parentElement.children[0].textContent;
    inputAuthor.value =
        ev.target.parentElement.parentElement.children[1].textContent;
}
async function deleteRecord(ev) {
    let urlToken = `${baseUrl}/${ev.target.id}`;
    try {
        let response = await fetch(urlToken, {
            method: "DELETE",
        });
        if (!response.ok) {
            throw new Error(`${response.status} ${response.statusText}`);
        }
        ev.target.parentElement.parentElement.remove();
        loadAll();
        return await response.json();
    } catch (error) {
        alert(error);
    }
}