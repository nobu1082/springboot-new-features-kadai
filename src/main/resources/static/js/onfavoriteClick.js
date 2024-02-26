function onFavoriteClick(houseId) {
  // API通信を行う処理
  fetch(`/api/favorite/${houseId}/toggle`)
    .then(response => response.json())
    .then(data => {
      // isFavorite を更新して、♡と❤️を切り替える
      document.getElementById("favorite-button").children[0].className = data.isFavorite ? "fas fa-heart" : "far fa-heart";
    });
}