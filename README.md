[![Codacy Badge](https://api.codacy.com/project/badge/Grade/3204f6548dc24ea6bc707d8343f1d7d2)](https://www.codacy.com/manual/monowai/mondrian?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=monowai/mondrian&amp;utm_campaign=Badge_Grade)

## Mondrian
A little command line drawing tool.  If you have Docker, there is an image you can run directly.

```$bash
docker run -it monowai/mondrian
``` 

### ADR
*   Java 11 - it's 2020 already... 
*   Micronaut is used as a lightweight command runner
*   PicoCli+Jline - Handle CLI, parameter sanitization and things like help
*   Lombok - because I know it and am not using this exercise to pickup Kotlin
*   Checkstyle - Google style
*   Docker for packaging 
*   Drawing routines refactored [this project](https://github.com/thangbn/console-drawing)

### Deviation
CTRL+D is used to quit instead of Q - convention over configuration

### Clone, Build & Run
```$bash
git clone https://github.com/monowai/mondrian.git
./gradlew build
java -jar build/libs/mondrian-0.1-all.jar
```

### Docker
You must first clone and build the JAR.
```$bash
# Build and run locally
docker build . -t monowai/mondrian --build-arg JAR_FILE=build/libs/mondrian-0.1-all.jar
docker run -it monowai/mondrian
```

### Spec
You're given the task of writing a simple console version of a drawing program. 
At this time, the functionality of the program is quite limited but this might change in the future. 
In a nutshell, the program should work as follows:
 1. Create a new canvas
 2. Start drawing on the canvas by issuing various commands
 3. Quit

### Commands

Command 		Description

`C w h`
           
Should create a new canvas of width w and height h.

`L x1 y1 x2 y2`
   
Should create a new line from (x1,y1) to (x2,y2). Currently only
                horizontal or vertical lines are supported. Horizontal and vertical lines
                will be drawn using the 'x' character.
                
`R x1 y1 x2 y2`
   
Should create a new rectangle, whose upper left corner is (x1,y1) and
                lower right corner is (x2,y2). Horizontal and vertical lines will be drawn
                using the 'x' character.
                
`B x y c`
         
Should fill the entire area connected to (x,y) with "colour" c. The
                behavior of this is the same as that of the "bucket fill" tool in paint
                programs.
                
`CTRL+D`
               
Should quit the program 

### Acceptance Criteria
Below is a sample run of the program. User input is prefixed with enter command:

#### Canvas object
```$bash
>C 20 4
----------------------
|                    |
|                    |
|                    |
|                    |
----------------------

```
#### Lines
```$bash
>L 1 2 6 2
----------------------
|                    |
|xxxxxx              |
|                    |
|                    |
----------------------


>L 6 3 6 4
----------------------
|                    |
|xxxxxx              |
|     x              |
|     x              |
----------------------
```

#### Rectangle

```$bash
>R 14 1 18 3
----------------------
|             xxxxx  |
|xxxxxx       x   x  |
|     x       xxxxx  |
|     x              |
----------------------

```
#### Fill
```$bash
>B 10 3 o
----------------------
|oooooooooooooxxxxxoo|
|xxxxxxooooooox   xoo|
|     xoooooooxxxxxoo|
|     xoooooooooooooo|
----------------------
```

enter command: CTRL+D
