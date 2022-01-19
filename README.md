A multi-thread video game written in Java by Lun Zhang 11/15/2021

# The spaceinvader game

Note: You need to make sure that the folder resources/images exists, and use at least jdk1.8 to compile the project

## Playing logic

Your spaceship (from the downside) needs to fight with enemy spaceships (from the upside) and avoid being shoot by them

There are different types of enemies with different weapons and health, you have 4 different weapons which have different properties and damage

If you were hit, you will lose health, the green bar at the left bottom corner shows your remaining health, when your health is reduced to 0, you will lose 1 life, there are only 3 lives to lose

Each time you kill an enemy you get 1 point, after you get over ***50 points***, the final boss will appear, if you beat it you will win
(Note: If you want to directly test fighting with the boss, you can change Controller/Game.java line 439 to "if (CommandCenter.getInstance().getScore() < 0) {")
得分超过50开启boss战

The power-up (bounded by green ring) includes adding health and starting shield, it will regularly appear at a random position in the map and you need to control the spaceship to "eat" it to get the buff

## Keyboard usage

use the arrow keys to move / 方向键移动

use the space bar to fire / 空格键设计

'S' to Start / S键开始游戏

'P' to Pause/Resume / P键开始游戏

'Q' to Quit / Q键退出游戏

use 1-4 to switch your weapon / 1-4键切换武器

1 - basic bullets / 基本子弹
2 - bullets in 3 directions / 散弹
3 - laser / 激光
4 - tracking missiles / 追踪导弹

'M' to Mute/Unmute the background music