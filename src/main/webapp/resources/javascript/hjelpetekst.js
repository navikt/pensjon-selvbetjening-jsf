(function ($) {
    "use strict";

    $(window).load(function () {
        var offsetlimit = 15;
        var hjelpetekst = $('.hjelpetekst-tooltip');

        // click on a infoikon
        $(document).on('click', '.infoikon.popup', function (evt) {
            var icon = evt.currentTarget;
            hjelpetekst = $(icon).siblings('.hjelpetekst-tooltip');
            var container = $('body,html');
            var scrollTop = $(document).scrollTop();
            var shouldAddClass = true;
            var windowWidth = $(window).width();

            // update the scrollbar to get it to show
            $(hjelpetekst).children('.ps-container').perfectScrollbar({suppressScrollX: true},'perfect-scrollbar');

            // hides or shows the tooltip
            if (hjelpetekst.hasClass('vis')) {
                shouldAddClass = false;
            }

            // remove vis from all hjelpeteksts and remove showleft to calculate correct offset
            $('.hjelpetekst-tooltip').removeClass('vis');
            $(hjelpetekst).children('.ps-container').mouseleave();
            hjelpetekst.removeClass('showleft');

            if (shouldAddClass) {
                hjelpetekst.addClass('vis');

                //To make the tooltip scrollable with keyboard
                $(hjelpetekst).children('.ps-container').mouseenter();
            }

            // calculates offset from right edge of hjelpetekst box to right side of screen
            var eRight = ($(window).width() - (hjelpetekst.offset().left + hjelpetekst.outerWidth()));

            if (eRight < offsetlimit) {
                hjelpetekst.addClass('showleft');
            }

            // jumps to the top of the tooltip if it is outside of screen
            if (scrollTop > hjelpetekst.offset().top) {
                container.scrollTop(hjelpetekst.offset().top - offsetlimit);
            }

            // force scroll the tooltip to make the scrollbar appear without the need for user interaction
            if (!$(hjelpetekst).children('.ps-container').scrollTop()) {
                $(hjelpetekst).children('.ps-container').scrollTop(hjelpetekst.scrollTop()+1).scrollTop(hjelpetekst.scrollTop()-1);
            }

            // prevents the default behavior of the link
            evt.preventDefault();
        });

        // closes the tooltip
        $(document).on('click', '.lukk-hjelpetekst', function (evt) {
            var lukk = evt.currentTarget;
            $(lukk).parents('.hjelpetekst-tooltip').removeClass('vis');
            $(lukk).parents('.hjelpetekst-tooltip').children('.ps-container').mouseleave();

            evt.preventDefault();
        });

        // closes the tooltip if the user clicks outside the tooltip
        $(document).on('click', function (evt) {
            if (!$(evt.target).closest('.hjelpetekst').length) {
                $('.hjelpetekst-tooltip').removeClass('vis');
                $('.hjelpetekst-tooltip').children('.ps-container').mouseleave();
            }
        });

        // on resize of window we have to move the hjelpetekst if the window is too small
        $(window).resize(function () {
            var arrowlengthcompensation = 55;
            var boxsize = hjelpetekst.width();

            if(typeof hjelpetekst.offset() != 'undefined'){ //No hjelpetekst on the page
                var eRight = ($(window).width() - (hjelpetekst.offset().left + hjelpetekst.outerWidth()));

                if (eRight < offsetlimit) {
                    hjelpetekst.addClass('showleft');
                } else if (eRight > (offsetlimit + boxsize - arrowlengthcompensation)) {
                    hjelpetekst.removeClass('showleft');
                }
            }
        });
    });
})(jQuery);
