[![Codacy Badge](https://api.codacy.com/project/badge/Grade/5af243e601db4d98b8ed4615b9724f42)](https://www.codacy.com/manual/monowai/mondrian?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=monowai/mondrian&amp;utm_campaign=Badge_Grade)

## Mondrian
A noddy little command lineData drawing tool

### ADR
*   Java 11 - it's 2020 already... 
*   Micronaut is used as a lightweight command runner
*   PicoCli+Jline - Handle commandline input, parameter sanitization and access to command objects
*   Lombok - because I know it and am not using this to pickup Kotlin
*   Checkstyle - Google style
*   Simple Docker to handle packaging 

### Deviation
CTRL+D is used to quit instead of Q - convention over configuration

### Clone, Build & Run
```$bash
git clone https://github.com/monowai/mondrian.git
./gradlew build
java -jar build/libs/mondrian-0.1-all.jar
```

### Docker
```$bash
docker build . -t mondrian:latest --build-arg JAR_FILE=build/libs/mondrian-0.1-all.jar
docker run -it mondrian
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
   
Should create a new lineData from (x1,y1) to (x2,y2). Currently only
                horizontal or vertical lines are supported. Horizontal and vertical lines
                will be drawn using the 'x' character.
                
`R x1 y1 x2 y2`
   
Should create a new rectangleData, whose upper left corner is (x1,y1) and
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
