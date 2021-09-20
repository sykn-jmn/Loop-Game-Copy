# Loop-Game-Copy
My own implementation of the Android Game Infinite Loop

### Game Goal
To have all ends of the loops connected by clicking on loops to change its orientation.

https://user-images.githubusercontent.com/56058545/133954271-9350b4e1-0e17-4a7a-8510-86c75df6163d.mp4


# How It Will Work (This might or might not be the development plan)

## Not So General Description
* Each game will have a 2D Loop Array which will be checked for completeness after every action.
* After a click, `MouseListener`'s `mousePressed` method will detect when a click has occured and return the click's x and y position. 
* To determine which loop was clicked, the x and y position of the mouseclick will be divided by the total length of the width or height to get the percentage of its position relative to the width or height. It will then be multiplied by the `Loop` array's height or width and then converted to integer.
* Using the `Loop` array, the clicked `Loop` will be returned and its method `rotate` will be activated which will update the values of the `Loop`'s pointers.
* After that, the `Loop` array will be iterated to check if all of its `Loop`'s pointers are pointed to another loop that is pointing to it as well.
* For Each Loop
  * For Each Pointer
    * IF NOT Pointer points down, the `Loop` at the bottom must be pointing up, right - left, etc. THEN return false
    * IF Pointer points outside the array i.e -1, or >= array size THEN return false
* Return True
* If True, Game Ends with Congratulations

## Kinds of Loops
* Source Loop
* I Loop
* T Loop
* L Loop
* Cross Loop

## Levels.txt
* first two lines are the  width and height respectively
* the following lines describe the level
 * 0 for no Loop
 * 1 for source Loop
 * 2 for I Loop
 * 3 for T Loop
 * 4 for L Loop
 * 5 for Cross Loop 
