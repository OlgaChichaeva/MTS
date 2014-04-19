function showSim(simId)
{
    // Перед началом загрузки отображаем анимацию.
    $("#simlayer").empty();
    $("#simlayer").append("<img src=\"/MTSweb/pics/wait.gif\" />");
    // Отсылаем паметры
    $.ajax({
        type: "GET",
        url: "/MTSweb/showSim",
        data: "sim_id=" + simId,
        // Выводим то что вернул PHP
        success: function(html) {
            //предварительно очищаем нужный элемент страницы
            $("#simlayer").empty();
            //и выводим ответ php скрипта
            $("#simlayer").append(html);
        }
    });

}