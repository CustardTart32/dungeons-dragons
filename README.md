# Project

## Aims

* Appreciate issues in user interface design

* Learn practical aspects of graphical user interface programming

* Learn more about the Java class libraries

* Learn the application of design patterns.

---

## Overview

You have received a request from a client for an application for the playing of dungeon-style puzzles. With a partner from your lab class, you will follow an agile development process to design and implement a desktop Java application that satisfies the requirements of the client (see below). The final piece of software you deliver is expected to be of professional quality, user-friendly, and demonstrate the knowledge and skills you have acquired in this course.           

## Preliminary client requirements

The client desires an application that lets the user move a player around a dungeon and try to overcome various challenges in order to "complete" the dungeon by reaching some goal. The simplest form of such a puzzle is a maze, where the player must find their way from the starting point to the exit.

![Maze][maze]

More advanced puzzles may contain things like boulders that need to be pushed onto floor switches,

![Boulders][boulders]

enemies that need to be fought with weapons, or collectables like potions and treasure.

![Advanced dungeon][advanced]

### Dungeon layout

To be specific, the layout of each dungeon is defined by a grid of squares, each of which may contain one or more entities. The different types of entities are as follows:

| Entity               | Example | Description                             |
| ------               | ------- | --------------------------------------- |
| Player               | ![Player][player] | Can be moved up, down, left, and right into adjacent squares, provided another entity doesn't stop them (e.g. a wall). |
| Wall                 | ![Wall][wall] | Blocks the movement of the player, enemies and boulders. |
| Exit                 | ![Exit][exit] | If the player goes through it the puzzle is complete.  |
| Treasure             | ![Treasure][treasure] | Can be collected by the player. |
| Door                 | ![Door][door_open] ![Door][door_closed] | Exists in conjunction with a single key that can open it. If the player holds the key, they can open the door by moving through it. Once open it remains so. The client will be satisfied if dungeons can be made with up to 3 doors. |
| Key                  | ![Key][key] | Can be picked up by the player when they move into the square containing it. The player can carry only one key at a time, and only one door has a lock that fits the key. It disappears once it is used to open its corresponding door. |
| Boulder              | ![Boulder][boulder] | Acts like a wall in most cases. The only difference being that it can be pushed by the player into adjacent squares. The player is only strong enough to push **one** boulder at a time. |
| Floor switch         | ![Floor switch][switch] | Switches behave like empty squares, so other entities can appear on top of them. When a boulder is pushed onto a floor switch, it is triggered. Pushing a boulder off the floor switch untriggers it. |
| Portal               | ![Portal][portal] | Teleports entities to a corresponding portal. |
| Enemy                | ![Enemy][enemy] | Constantly moves toward the player, stopping if it cannot move any closer. The player dies upon collision with an enemy. |
| Sword                | ![Sword][sword] | This can be picked up the player and used to kill enemies. Only one sword can be carried at once. Each sword is only capable of 5 hits and disappears after that. One hit of the sword is sufficient to destroy any enemy. |
| Invincibility potion | ![Invincibility][invincibility] | If the player picks this up they become invincible to enemies. Colliding with an enemy should result in their immediate destruction. Because of this, all enemies will run away from the player when they are invincible. The effect of the potion only lasts a limited time. |

### Goals

In addition to its layout, each dungeon also has a goal that defines what must be achieved by the player for the dungeon to be considered complete. Basic goals are:

* Getting to an exit.
* Destroying all enemies.
* Having a boulder on all floor switches.
* Collecting all treasure.

More complex goals can be built by logically composing basic goals. For example,

* Destroying all enemies AND getting to an exit
* Collecting all treasure OR having a boulder on all floor switches
* Getting to an exit AND (destroying all enemies OR collecting all treasure)

If getting to an exit is one of a conjunction of conditions, it must be done last. For example, if the condition is to destroy all enemies AND get to an exit, the player must destroy the enemies *then* get to the exit.


[player]:        images/human_new.png
[wall]:          images/brick_brown_0.png
[exit]:          images/exit.png
[door_open]:     images/open_door.png
[door_closed]:   images/closed_door.png
[key]:           images/key.png
[boulder]:       images/boulder.png
[switch]:        images/pressure_plate.png
[portal]:        images/portal.png
[enemy]:         images/deep_elf_master_archer.png
[sword]:         images/greatsword_1_new.png
[invincibility]: images/brilliant_blue_new.png
[treasure]:      images/gold_pile.png

[maze]:          examples/maze.png
[boulders]:      examples/boulders.png
[advanced]:      examples/advanced.png
