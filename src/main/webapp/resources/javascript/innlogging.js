$(function () {
    var container = $(".login-container"),
        username = $(".logged-in-user-name").text(),
        logoutDoesntExistOrIsNotHidden = container.find("#auth-btns #logout").length < 1 || !container.find("#auth-btns #logout").hasClass("hidden"),
        loginExistsAndIsNotHidden = container.find("#auth-btns #login").length > 0 && !container.find("#auth-btns #login").hasClass("hidden");

    container.find("#auth-btns #logout")
        .on("click", function () {
            var jsLogout = $(".logged-in-user-logout-button").attr("onclick").replace("return ", "");
            if (jsLogout) {
                eval(jsLogout);
            }
        });

    if (logoutDoesntExistOrIsNotHidden || loginExistsAndIsNotHidden) {
        return;
    }

    // user name
    container.find("#login-details").removeClass("hidden");
    container.find("#name-container img").hide();
    container.find("#name").text(username);

    // logout button
    container.find("#auth-btns").show();
    container.find("#auth-btns #logout").removeClass("hidden");

    // remove unwanted tooltips
    container.find(".logout-tooltip").remove();
    container.find(".login-tooltip").remove();

    container.show();
});
