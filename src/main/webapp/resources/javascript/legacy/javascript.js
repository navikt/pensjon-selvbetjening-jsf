function addLoadEvent(func) {
    var oldonload = window.onload;
    if (typeof window.onload != 'function') {
        window.onload = func;
    } else {
        window.onload = function () {
            if (oldonload) {
                oldonload();
            }
            func();
        }
    }
}

function setFocusOnLoad(formName, elementName) {
    addLoadEvent(function () {
        var f = document.forms[formName];
        if (f) {
            var elem = f.elements[elementName];
            if (elem) elem.focus();
        }
    });
}

function setFocusOnLoadByIndex(formName, elementName, index) {
    addLoadEvent(function () {
        var f = document.forms[formName];
        if (f) {
            var elem = f.elements[elementName][index];
            if (elem) elem.focus();
        }
    });
}

function setFocusOnLoadById(elementId) {
    addLoadEvent(function () {
        var f = document.getElementById(elementId);
        if (f) {
            f.focus();
        }
    });
}

//Added as a result of SIR #36519
function setFocusById(elementId) {
    var f = document.getElementById(elementId);
    if (f) {
        f.focus();
    }
}

function setEditable(id, isEditable) {
    $("#" + id).attr("disabled", !isEditable);
}

function setEditableOnLoad(elementId, isEditable) {
    addLoadEvent(function () {
        setEditable(elementId, mode);
    });
}

function getVisibility(id) {
    if (document.getElementById) {
        var style = document.getElementById(id).style;
        return style;
    }
}

function setVisibility(id, visibility) {
    if (document.getElementById) {
        document.getElementById(id).style.display = visibility;
    }
}

function pageLoad() {
    setFormDisabled();
    setUtvandret(false);
}

function changeVisibility(id) {
    if (document.getElementById) {
        var style = document.getElementById(id).style;
        if (style.display == "none") {
            style.display = "block";
        } else if (style.display == "block") {
            style.display = "none";
        }
    }
}

function disableCheckbox(checkboxId) {
    if (document.getElementById) {
        if (document.getElementById(checkboxId).disabled) {
            document.getElementById(checkboxId).disabled = false;
        } else {
            document.getElementById(checkboxId).disabled = true;
            document.getElementById(checkboxId).checked = false;
        }
    }
}

function getCheckedValue(radioObj) {
    if (!radioObj) {
        return "";
    }

    var radioLength = radioObj.length;

    if (radioLength == undefined) {
        if (radioObj.checked) {
            return radioObj.value;
        } else {
            return "";
        }
    }

    for (var i = 0; i < radioLength; i++) {
        if (radioObj[i].checked) {
            return radioObj[i].value;
        }
    }

    return "";
}

function validateFnr(fieldId, errorMsgId) {
    if (document.getElementById) {
        var value = document.getElementById(fieldId).value;
        if (value.length == 0 || value.length == 11) {
            document.getElementById(errorMsgId).style.display = "none";
        }
        else {
            document.getElementById(errorMsgId).style.display = "block";
            document.getElementById(fieldId).focus();
        }
    }
}

function showWhatIf(dropdownId, max) {
    if (document.getElementById) {
        var dropdown = document.getElementById(dropdownId);
        var idx = dropdown.selectedIndex;
        for (i = 0; i <= max; i++) {
            if (i == dropdown.options[idx].value) {
                document.getElementById(dropdownId + '_' + i).style.display = "block";
            }
            else {
                document.getElementById(dropdownId + '_' + i).style.display = "none";
            }
        }
    }
}

function setVisibility(id, visibility) {
    if (document.getElementById) {
        document.getElementById(id).style.display = visibility;
    }
}

function changeScreenSize(w, h) {
    window.resizeTo(w, h)
}

function MM_goToURL() { //v3.0
    var i, args = MM_goToURL.arguments;
    document.MM_returnValue = false;
    for (i = 0; i < (args.length - 1); i += 2) eval(args[i] + ".location='" + args[i + 1] + "'");
}

function MM_openBrWindow(theURL, winName, features) { //v2.0
    win = window.open(theURL, winName, features);
    win.moveTo(300, 300);
}

function sendForm(button) {
    document.getElementById(button).click();
}

function submitForm(event, link) {
    if (event.keyCode == 13) {
        document.getElementById(link).click();
        return false;
    }

    return true;
}

function setFocusOnLoadByIdIfVisible(elementId, visible) {
    if (visible == true) {
        setFocusOnLoadById(elementId);
    }
}

function setFocusByIdIfVisible(elementId, visible) {
    if (visible == true) {
        setFocusById(elementId);
    }
}

function setFocusOnFirstVisibleElementInForenkletBeregning() {
    var f = document.getElementById('forenkletSimulering:hjelpNyAlderspensjon');
    if (f) {
        f.focus();
    }
    f = document.getElementById('forenkletSimulering:hjelpDagensAlderspensjon');
    if (f) {
        f.focus();
    }
    f = document.getElementById('forenkletSimulering:hjelpOvergangsregler');
    if (f) {
        f.focus();
    }
    f = document.getElementById('forenkletSimulering:nokkeltallBeregningTable:0:showBeregningLink');
    if (f) {
        f.focus();
    }
}

function scrollToYPosOfSelectedStepNavigatorPane(mainStepSelector) {
    scrollToElementYPos(mainStepSelector, 350);
}

function scrollToElementYPos(selector, duration) {
    var element = $(selector).get(0),
        duration = duration || 350; // uses default animation duration of 350 ms

    if (element) {
        $('html, body').animate({
            scrollTop: $(element).offset().top
        }, duration);
    }
}

function hideAlignmentHelp(displayAreaId, inputId) {
    helpArea = document.getElementById(displayAreaId);
    helpArea.style.display = 'none';

    if (inputId != null && inputId != '' && inputId != 'null') {
        input = document.getElementById(inputId).focus();
    }
}

function setFocusOnLoadByIndexIfVisible(formName, elementName, index, visible) {
    if (visible == true) {
        setFocusOnLoadByIndex(formName, elementName, index);
    }
}

//Added in F3V2A for byggeklosser
function getBrowserWidth() {
    if (window.innerWidth) {
        return window.innerWidth;
    } else if (document.documentElement && document.documentElement.clientWidth != 0) {
        return document.documentElement.clientWidth;
    } else if (document.body) {
        return document.body.clientWidth;
    }

    return 0;
}

function resizeImages(smallImagesDiv, largeImagesDiv) {
    addLoadEvent(function () {
        var browserWidth = getBrowserWidth();
        if (browserWidth < 1280) {
            document.getElementById(smallImagesDiv).style.display = "inline";
        } else {
            document.getElementById(largeImagesDiv).style.display = "inline";
        }
    });
}

function getBrowserDependentCSS(csspath) {
    browser_version = parseInt(navigator.appVersion);
    browser_type = navigator.appName;
    var css = "<link rel=\"stylesheet\" type=\"text/css\" href=\"" + csspath + "/css/ie.css\" />"

    if (browser_type == "Microsoft Internet Explorer") {
        document.write(css);
    }
}

function onSuccessHandler(data, func) {
    if (data.status == "success") {
        func();
    }
}

function verifySubmit(enabled, message) {
    if (enabled) {
        if (!confirm(message)) {
            return false;
        }
    }
}

function showModalWindow(elementId) {
    $("#" + elementId).css('display', 'inline');
}

function hideModalWindow(elementId) {
    $("#" + elementId).css('display', 'none');
}

function openWindow(url, target, width, height) {
    window.open(url, target, 'width=' + width + ',height=' + height + ', scrollbars=yes');
}

function numberWithSpaces(number) {
    return !isNaN(number) ? number.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ' ') : '';
}