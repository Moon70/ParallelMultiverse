# Parallel Multiverse

This is **Abyss**´ contribution to the [Revision 2020](https://2020.revision-party.net "Revision") [Demoscene](https://en.wikipedia.org/wiki/Demoscene "Wikipedia") event.

[Demozoo](https://demozoo.org/productions/277294/)

[Pouet](https://www.pouet.net/prod.php?which=85333/)

[YouTube](https://www.youtube.com/watch?v=PMrRIBPadLE)

[Download binary from scene.org (including Java runtime)](https://files.scene.org/get/parties/2020/revision20/pc-demo/abyss_parallelmultiverse___including_java_runtime.zip)

Download binary without Java runtime: Look at 'Releases' right here on GitHub


### Credits ###

#### Music by Neurodancer: ####

* **Drop your baggage** taken from the album [znooQ´d](https://neurowerx.bandcamp.com/album/znooqd "neurowerx.bandcamp.com").



#### Graphics: ####

* Abyss logo by Celtic/Axis, used before in the Pulstar demo.
* Eyeball by Tyshdomos, used before in the Drugstore demo.



#### Code: ####

This is my first demo in this millenium. It is written in pure native naked Java, which means i haven´t included any external API, no OpenGL, no DirectX, no esoteric stuff of any kind.

Why the hell: You hardly can continue making demos from the point where you stopped 23 years ago. So when i discovered [Coda](https://www.pouet.net/prod.php?which=80998 "Pouet") which gave me the motivation to make 'one more demo', i decided not to restart on Amiga, but to try Java. Knowing that Java is far from being the perfect language for that job. Knowing that accurate timing is hardly possible, taking over the CPU simply impossible. That not enough, i also decided not to play around with outdated OpenGL Java APIs, but to use my own engine which simply promotes an integer array as chunky buffer. With this limitations, creating the demo was in fact real fun.



Technical comments:

- Some parts of the code may look odd. Please keep in mind, for a 'realtime application' like a demo, Java's garbage collector is your enemy. You really don't want this guy to disturb your timing.
- Starting the demo with commandline option `-Dsun.java2d.opengl=true` could give an enormours performance boost, especially on older hardware, so why isn´t this option set by default?
  Well, it was. On my elder computer, sending the chunky buffer to the screen takes 29 milliseconds (which drops the framerate to 30 fps). This option decreases the value to 5 milliseconds, resulting in perfect 60 fps.
  BUT: While testing the demo, i discovered the following problem on Windows 10: Having a two-screen-system, it´s mandatory that the screen which Windows calls 'number 1' is set as primary screen (which is default). If you change that, you see just nothing. I could reproduce that on two systems. Too bad i could´t find a workaround in time, so i had to drop that option.



### How to build: ###

If you´re not familiar with the build tool 'Apache Maven', these few lines might help:

* download and unzip [Apache Maven](http://maven.apache.org/download.cgi)
* add the **...\apache-maven-x.x.x\bin** folder to your path variable
* check out both [LunarEngine](https://github.com/Moon70/LunarEngine) and [ParallelMultiverse](https://github.com/Moon70/ParallelMultiverse)
* open CMD, change directory to lunarengine folder, that one which contains the pom.xml
* execute this command: **mvn clean install**
* now the engine should be compiled and installed in you local maven repository
* change directory to parallelmultiverse folder, again that one containing the pom.xml
* execute this command: **mvn clean package**
* now the target folder should contain two .jar files, the bigger one (containing also the engine) is the executable one

