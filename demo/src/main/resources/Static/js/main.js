    function toogle() {
    var element = document.getElementById('ct-trabajo');
    var element2 = document.getElementById('ct-publicacion');
    var element3 = document.getElementById('ct-trabajos');
    var element4 = document.getElementById('ct-publicaciones');
  if (element.style.display === 'none') {
    element.style.display = 'block';
    element3.style.display = 'block';
    element2.style.display = 'none';
    element4.style.display = 'none';
    document.getElementById('btn').innerText = 'Publicacion';
  } else {
    element.style.display = 'none';
    element3.style.display = 'none';
    element2.style.display = 'block';
    element4.style.display = 'block';
    document.getElementById('btn').innerText = 'Trabajo';
  }
}