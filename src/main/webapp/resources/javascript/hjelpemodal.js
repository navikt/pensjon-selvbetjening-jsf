(function ($) {
    "use strict";
    $(document).ready(function () {
        $(".modal").on('shown', function () {
            var modal = $(this);
            var modalFousId = modal.attr("data-foucus-id");
            if (modalFousId != null && modal.find(modalFousId).length > 0 && modalFousId.length != 0) {
                //Reseting offset inside the modal
                modal.animate({scrollTop: 0}, 0);

                var initialOffset = $(document).scrollTop();
                var focusOffset = modal.find(modalFousId).offset().top;

                var scrollToOffset = focusOffset - initialOffset;
                scrollToOffset = (scrollToOffset < 0) ? 0 : scrollToOffset;

                modal.animate({scrollTop: scrollToOffset}, 20);
            }
        });
    });
})(jQuery);
