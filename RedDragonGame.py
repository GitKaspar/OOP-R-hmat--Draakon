import random

import pygame

pygame.init()

#EKRAANI LOOMINE
WINDOW = pygame.display.set_mode((1300, 700))
pygame.display.set_caption("The Red Dragon")

#KAUSTA NIMI, KUS KÕIK PILDID ASUVAD
RESOURCE_FOLDER = "res/"

#MÄNGU TAUST
bg = pygame.image.load(RESOURCE_FOLDER + "bg.jpg")

clock = pygame.time.Clock()

#MÄNGIJA KLASS, KUS ASUVAD KÕIK ANIMATSIOONI PILDID, PARAMEETRID JA FUNKTSIOONID
class Player:
    sprites = [[pygame.image.load(RESOURCE_FOLDER + "3.png"),
                pygame.image.load(RESOURCE_FOLDER + "4.png"),
                pygame.image.load(RESOURCE_FOLDER + "5.png")],
               [pygame.image.load(RESOURCE_FOLDER + "6.png"),
                pygame.image.load(RESOURCE_FOLDER + "7.png"),
                pygame.image.load(RESOURCE_FOLDER + "8.png")],
               [pygame.image.load(RESOURCE_FOLDER + "9.png"),
                pygame.image.load(RESOURCE_FOLDER + "10.png"),
                pygame.image.load(RESOURCE_FOLDER + "11.png")],
               [pygame.image.load(RESOURCE_FOLDER + "0.png"),
                pygame.image.load(RESOURCE_FOLDER + "1.png"),
                pygame.image.load(RESOURCE_FOLDER + "2.png")]]

    def __init__(self, x, y):
        self.x = x
        self.y = y
        self.width = 191
        self.height = 161
        self.speed = 10
        self.direction = 0
        self.fly_count = 0
        self.hitbox = (self.x + 20, self.y, 28, 60)
        self.fire_cooldown = 300
        self.fire_last = -self.fire_cooldown
        self.remove = False

        self.health = 5
        self.damage_cooldown = 500
        self.damage_last = 0

        self.coins = 0

#JÄLGIB KAS MÄNGIJA ON VEEL ELUS
    def update(self):
        self.fly_count = (self.fly_count + 1) % 9
        self.hitbox = (self.x + 40, self.y + 50, 120, 70)
        self.check_collision()
        if self.health == 0:
            self.remove = True

#JÄLGIB KAS MÄNGIJA ON VAENLASELE VASTU LÄINUD
    def check_collision(self):
        t = pygame.time.get_ticks()
        if t < self.damage_last + self.damage_cooldown:
            return
        for e in enemies:
            if self.hitbox[0] + self.hitbox[2] >= e.hitbox[0] and self.hitbox[0] <= e.hitbox[0] + e.hitbox[2] and self.hitbox[1] + self.hitbox[3] >= e.hitbox[1] and self.hitbox[1] <= e.hitbox[1] + e.hitbox[3]:
                self.damage_last = t
                self.hit()
#MÄNGIJA ELUD VÄHENEVAD ÜHE VÕRRA
    def hit(self):
        self.health -= 1

#MÜNTIDE ARV TÕUSED ÜHE VÕRRA
    def collect(self):
        self.coins += 1

#MÄNGIJA LOOMINE
    def draw(self):
        WINDOW.blit(self.sprites[self.direction][self.fly_count // 3], (self.x, self.y))

#MÄNGIJA LIIKUMINE PAREMALE, ALLA, VASAKULE, ÜLES
    def move_right(self):
        self.direction = 0
        if self.x < WINDOW.get_size()[0] - self.width: 
            self.x += self.speed

    def move_down(self):
        self.direction = 1
        if self.y < WINDOW.get_size()[1] - self.height:  
            self.y += self.speed

    def move_left(self):
        self.direction = 2
        if self.x > 0: 
            self.x -= self.speed

    def move_up(self):
        self.direction = 3
        if self.y > 0: 
            self.y -= self.speed

#MÄNGIJA POOLT TULISTAMINE, KUI PALJU AEGA PEAB OLEMA IGA TULISTAMISE VAHEL, SUUNAD
    def fire(self):  
        t = pygame.time.get_ticks()
        if t < self.fire_last + self.fire_cooldown:
            return
        self.fire_last = t
        x = self.x + self.width // 2
        y = self.y + self.height // 2
        if self.direction == 0:
            x += 50
        if self.direction == 1:
            y += 50
        if self.direction == 2:
            x -= 50
        if self.direction == 3:
            y -= 50
        fires.append(Fire(x, y, self.direction))

#ELUDE KLASS, KUS ASUVAD KÕIK PILDID, PARAMEETRID JA FUNKTSIOONID
class HealthBar:
    sprites = [pygame.image.load(RESOURCE_FOLDER + "healthbar_0.png"),
                pygame.image.load(RESOURCE_FOLDER + "healthbar_1.png"),
                pygame.image.load(RESOURCE_FOLDER + "healthbar_2.png"),
                pygame.image.load(RESOURCE_FOLDER + "healthbar_3.png"),
                pygame.image.load(RESOURCE_FOLDER + "healthbar_4.png"),
                pygame.image.load(RESOURCE_FOLDER + "healthbar_max.png")]
    def __init__(self):
        #ELUDE ARV
        self.health = 5
        for i in range(len(self.sprites)):
            self.sprites[i] = pygame.transform.scale(self.sprites[i], (240, 80))

    def update(self, health):
        self.health = health

    def draw(self):
        WINDOW.blit(self.sprites[self.health], (20, 20))

#PUNKTIDE(KULDMÜNTIDE) KLASS, KUS ASUVAD KÕIK PILDID, PARAMEETRID JA FUNKTSIOONID
class Coin:
    sprite = pygame.image.load(RESOURCE_FOLDER + "coin.png")
    def __init__(self, x=None, y=None):
        self.width = 60
        self.height = 60
#KUI EI OLE X JA Y PARAMEETRIT ETTE ANTUD, SIIS VALITAKSE NEED SUVAKALT ARVAESTADES EKRAANI SUURUST
        self.x = x if x else random.randint(0, WINDOW.get_size()[0] - self.width)
        self.y = y if y else random.randint(0, WINDOW.get_size()[1] - self.height)
        self.sprite = pygame.transform.scale(self.sprite, (self.width, self.height))
        self.remove = False

#KONTROLLIB KAS MÄNGIJA ON PUNKTI SAANUD
    def check_collision(self):
        x = self.x + self.width // 2
        y = self.y + self.height // 2
        if 0 < x - dragon.hitbox[0] < dragon.hitbox[2] and 0 < y - dragon.hitbox[1] < dragon.hitbox[3]:
            dragon.collect()
            self.remove = True

    def update(self):
        self.check_collision()


    def draw(self):
        WINDOW.blit(self.sprite, (self.x, self.y))

#KÕIKIDE PUNKTIDE KLASS, KUS ASUVAD KÕIK PARAMEETRID JA FUNKTSIOONID
class Purse:
    sprite = pygame.image.load(RESOURCE_FOLDER + "coinicon.png")
    def __init__(self):
        self.sprite = pygame.transform.scale(self.sprite, (70, 70))
        self.count = 0
        self.font = pygame.font.Font("freesansbold.ttf", 32)

    def update(self, count):
        self.count = count

#JOONISTAB EKRAANI NURKA PUNKTIDE LOENDURI JA NÄITAB KUI PALJU PUNKTE ON KOGUTUD
    def draw(self):
        text = self.font.render(f"{self.count}/{coins_goal}", True, (255, 213, 128))
        WINDOW.blit(self.sprite, (WINDOW.get_width() - 170, 30))
        WINDOW.blit(text, (WINDOW.get_width() - 100, 50))

#VAENLASE KLASS, KUS ASUVAD KÕIK ANIMATSIOONIDE PILDID, PARAMEETRID JA FUNKTSIOONID
class Enemy:
    sprites = [[pygame.image.load(RESOURCE_FOLDER + "tile008.png"),
                pygame.image.load(RESOURCE_FOLDER + "tile009.png"),
                pygame.image.load(RESOURCE_FOLDER + "tile010.png"),
                pygame.image.load(RESOURCE_FOLDER + "tile011.png")],
               [pygame.image.load(RESOURCE_FOLDER + "tile004.png"),
                pygame.image.load(RESOURCE_FOLDER + "tile005.png"),
                pygame.image.load(RESOURCE_FOLDER + "tile006.png"),
                pygame.image.load(RESOURCE_FOLDER + "tile007.png")]]

    def __init__(self, x=None, y=None, direction=None):
        self.width = 96
        self.height = 96
        self.fly_count = 0
        self.x = x if x else random.randint(0, WINDOW.get_size()[0] - self.width)
        self.y = y if y else random.randint(0, WINDOW.get_size()[1] - self.height)
        self.direction = direction if direction else random.choice([-1, 1])
        self.speed = 4
        self.speed_delta = 0.1
        self.remove = False
        self.hitbox = (self.x + 10, self.y + 5, self.width - 20, self.height - 10)

#ELUDE ARV
        self.health = 3

    def update(self):
        self.fly_count = (self.fly_count + 1) % 12
        self.move()

        self.hitbox = (self.x + 10, self.y + 5, self.width - 20, self.height - 10)

#JOONISTAB SUVAKALE X JA Y KOORDINAADILE VAENLASE
    def draw(self):
        if self.direction == 1:
            WINDOW.blit(self.sprites[0][self.fly_count // 3], (self.x, self.y))
        else:
            WINDOW.blit(self.sprites[1][self.fly_count // 3], (self.x, self.y))

    def move(self):
        if self.x <= 0 or self.x >= WINDOW.get_size()[0] - self.width:
            self.direction = -self.direction
        self.x += (self.speed + self.speed_delta * difficulty) * self.direction

#VÕTAB VANELASELT ÜHE ELU MAHA KUI TA ON PIHTA SAANUD
    def hit(self):
        self.health -= 1
#KUI VANELASEL ON 0 ELU, SIIS TA EEMALDATAKSE EKRAANILT
        if self.health == 0:
            self.remove = True

#TULISTAMISE KLASS, KUS ASUVAD KÕIK PILDID, PARAMEETRID JA FUNKTSIOONID
class Fire:
    sprite = pygame.image.load(RESOURCE_FOLDER + "fire.png")

    def __init__(self, x, y, direction):
        self.direction = direction
        self.speed = 15
        self.remove = False
        self.scale = 80
        self.x = x
        self.y = y
        if direction == 0:
            self.y -= self.scale // 2
        if direction == 1:
            self.x -= self.scale // 2
        if direction == 2:
            self.x -= self.scale
            self.y -= self.scale // 2
        if direction == 3:
            self.x -= self.scale // 2
            self.y -= self.scale

        self.sprite = pygame.transform.scale(self.sprite, (self.scale, self.scale))

#KUI TULI LIIGUB EKRAANI ÄÄRDE, SIIS TA EEMALDATAKSE EKRAANILT
    def update(self):
        self.move()
        if self.x < -self.scale or self.x > WINDOW.get_size()[0] or self.y < -self.scale or self.y > WINDOW.get_size()[
            1]:
            self.remove = True
            return
        self.check_collision()

#KONTROLLIB KUHU SUUNAS TULI PEAKS LENDAMA OLENEVALT MÄNGIJA SUUNAST
    def move(self):
        if self.direction == 0:
            self.x += self.speed
        if self.direction == 1:
            self.y += self.speed
        if self.direction == 2:
            self.x -= self.speed
        if self.direction == 3:
            self.y -= self.speed

#KONTROLLIB KAS TULI LÄHEB VAENLASE PIHTA JA SEL JUHUL EEMALDAB TULE EKRAANILT
    def check_collision(self):
        x = self.x + self.scale // 2
        y = self.y + self.scale // 2
        for e in enemies:
            if 0 < x - e.hitbox[0] < e.hitbox[2] and 0 < y - e.hitbox[1] < e.hitbox[3]:
                e.hit()
                self.remove = True

#JOONISTAB TULE EKRAANILE
    def draw(self):
        WINDOW.blit(self.sprite, (self.x, self.y))
        # pygame.draw.circle(WINDOW, (0, 0, 0), (self.x, self.y), 10)

#KONTROLLIB VÕITU
def redraw_game_window():
    global fires, enemies, game_over, coins
    WINDOW.blit(bg, (0, 0))
    if dragon.coins == coins_goal:
        game_over = True
    if not game_over:
        dragon.update()
    if not dragon.remove:
        dragon.draw()

    new_fires = []
    new_enemies = []
    new_coins = []

    for fire in fires:
        fire.update()
        if not fire.remove:
            fire.draw()
            new_fires.append(fire)
    for enemy in enemies:
        enemy.update()
        if not enemy.remove:
            enemy.draw()
            new_enemies.append(enemy)
    for coin in coins:
        coin.update()
        if not coin.remove:
            coin.draw()
            new_coins.append(coin)

    fires = new_fires
    enemies = new_enemies
    coins = new_coins

    health_bar.update(dragon.health)
    health_bar.draw()
    purse.update(dragon.coins)
    purse.draw()

#MÕNGU LÕPP JA KUVATAKSE KAOTUSE EKRAAN
    if dragon.remove:
        game_over = True
        sprite = pygame.image.load(RESOURCE_FOLDER + "endred.png")
        WINDOW.blit(sprite, ((WINDOW.get_width() - sprite.get_width()) / 2, (WINDOW.get_height() - sprite.get_height()) / 2))
#MÄNGU LÕPP JA KUVATAKSE VÕIDU EKRAAN
    elif game_over:
        sprite = pygame.image.load(RESOURCE_FOLDER + "win.png")
        WINDOW.blit(sprite, ((WINDOW.get_width() - sprite.get_width()) / 2, (WINDOW.get_height() - sprite.get_height()) / 2))
    pygame.display.update()


# mainloop
#MÄNGIJA, ELUDE JA ELUDE LOENDURI ISENDID
dragon = Player(530, 300)
health_bar = HealthBar()
purse = Purse()

#VAENLASTE TEKKIMISE INTERVALL  
enemies = []
enemy_spawn_interval_min = 3_000
enemy_spawn_interval_max = 15_000
enemy_spawn_interval = enemy_spawn_interval_max
enemy_spawn_next = 0
#VAENLASTE RASKUSASTME TÕUSMINE AJAGA
difficulty = 1
difficulty_increase_delta = 0.1
difficulty_increase_interval = 100  # ms
difficulty_increase_next = difficulty_increase_interval

fires = []

coins = []
#PUNKTIDE TEKKIMISE INTERVALL
coin_spawn_interval_min = 3_000
coin_spawn_interval_max = 10_000
coin_spawn_next = coin_spawn_interval_min
coins_goal = 10

run = True
game_over = False
while run:
    clock.tick(27)
    t = pygame.time.get_ticks()
    keys = pygame.key.get_pressed()

    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            run = False

    if game_over:
        if keys[pygame.K_SPACE]:
            game_over = False
            dragon = Player(530, 300)
            dragon.fire_last = t + 2
            enemies = []
            fires = []
            coins = []
            enemy_spawn_next = t
            difficulty = 1
            difficulty_increase_next = t + difficulty_increase_interval

    else:
        if t >= difficulty_increase_next:
            difficulty += difficulty_increase_delta
            difficulty_increase_next = difficulty_increase_interval

        if t >= enemy_spawn_next:
            enemies.append(Enemy())
            enemy_spawn_next = t + enemy_spawn_interval_min + \
                               random.randint(0, enemy_spawn_interval_max - enemy_spawn_interval_min) * (1/difficulty)

        if t >= coin_spawn_next:
            coins.append(Coin())
            coin_spawn_next = t + random.randint(coin_spawn_interval_min, coin_spawn_interval_max)

#ERINEVATE KLAHVIDE ÜLESANNE PROGRAMMIS
        if keys[pygame.K_SPACE]:
            dragon.fire()

        if keys[pygame.K_UP]:
            dragon.move_up()
        if keys[pygame.K_DOWN]:
            dragon.move_down()
        if keys[pygame.K_LEFT]:
            dragon.move_left()
        if keys[pygame.K_RIGHT]:
            dragon.move_right()

    redraw_game_window()
    