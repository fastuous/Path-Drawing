# Path Drawing

This module allows the user to tap the screen to add a vertex to a list of vertices. It then draws a smoothed curve along those vertices in the order that you added them to the list. The path is a cubic spline, specifically a Catmull Rom spline with what appears to be uniform knot parametrization. 

This module is part of a LibGDX project, and I've separated it out to be its own LibGDX project. If you know how to import a LibGDX project into your IDE of choice, you can simply clone this repository and treat it as any normal gradle project.

If you don't know how to set up a gradle and/or LibGDX project, you can find info on it <a target="_blank" href="http://libgdx.badlogicgames.com/documentation.html">here</a>.

The main program code is loated in core/src/com/trumandeyoung/pathdrawing/PathDrawing.java.

![Imgur](http://imgur.com/VGoXtmh)
