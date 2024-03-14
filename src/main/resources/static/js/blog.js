const deleteButton = document.getElementById('delete-btn');

if (deleteButton) {
  deleteButton.addEventListener('click', event => {
    let id = document.getElementById('article-id').value;

    fetch(`/blogs/${id}`, {
      method: 'DELETE'
    }).then(() => {
      alert('삭제가 완료되었습니다.');
      location.replace(`/blogs`);
    })
  })
}