$(function() {
      var $slides = $('#slides');

      Hammer($slides[0]).on("swipeleft", function(e) {
        $slides.data('superslides').animate('next');
      });

      Hammer($slides[0]).on("swiperight", function(e) {
        $slides.data('superslides').animate('prev');
      });

      $slides.superslides({
		play : 3000,
        slide_easing: 'easeInOutCubic',
		slide_speed: 800,
		pagination: true,
		hashchange: false,
		scrollable: true
      });
    });