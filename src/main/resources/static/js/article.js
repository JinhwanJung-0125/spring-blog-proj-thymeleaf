const modifyButton = document.getElementById('modify-btn');
const creationButton = document.getElementById('create-btn');

if (modifyButton) {
  modifyButton.addEventListener('click', event => {
    let params = new URLSearchParams(location.search);
    let id = params.get('id');

    fetch(`/blogs/${id}`, {
      method: 'PUT',
      headers: {
        'Content-Type': "application/json",
      },
      body: JSON.stringify({
        title: document.getElementById('title').value,
        context: document.getElementById('context').value
      })
    }).then(() => {
      alert('수정이 완료되었습니다.');
      location.replace(`/blogs/${id}`);
    })
  })
}

if (creationButton) {
  creationButton.addEventListener('click', event => {
    fetch(`/blogs`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        title: document.getElementById('title').value,
        context: document.getElementById('context').value
      })
    }).then(() => {
      alert("등록 완료되었습니다.");
      location.replace("/blogs");
    })
  })
}