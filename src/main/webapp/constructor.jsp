<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:import url="elements/header.jsp"></c:import>
<html>
<head>
    <title>Конструктор квестов</title>
    <link rel="stylesheet" href="styles/styles.css">
</head>
<body class="typewriter">
<div><h1>Конструктор квестов</h1></div>
<div>
    <form action="/constructor" method="POST">
        <fieldset>
        <div class="input-group">
            <div class="input-group-prepend">
                <span class="input-group-text">Сценарий квеста</span>
            </div>
            <textarea class="form-control" aria-label="With textarea" name="questScenario"
                      placeholder="Напишите здесь сценарий вашего квеста. Руководство по сценарию и пример - ниже"></textarea>
        </div>
        <button type="submit" class="button button-green">Создать квест</button>
        </fieldset>
    </form>
</div>
<br>
<div><h1>Инструкция по созданию квеста</h1></div>
<div class="largeText">
    Минимальный набор атрибутов квеста: Quest, Hero и Event1. Quest и Hero оформляются одним способом - посмотрите в примере ниже.<br>
    Событие же может быть оформлено разными способами в зависимости от типа события.<br>
    Тип события определяется в строке, следующей за ним: 1) Q - это вопрос, 2) F - это бой, 3) Win - это победа, 4) Lose - это поражение.<br>
    Для вопроса требуется указать варианты ответов и события, на которые ведут ответы.<br>
    Для боя необходимо указать врага (villain) и события, на которые ведут победа (Good) и поражение (Bad) в бою.<br>
    Для Win и Lose необходимо указать только сообщение.<br>
    После каждого события необходимо использовать оператор Break.<br>
</div>

<div><h1>Пример квеста</h1></div>
<div class="largeText">
    Quest:На деревню в Черноземье?;<br>
    Hero:strength=6^health=50;<br>
    <br>
    Event1:Начало.;<br>
    Q:Душным летним вечером пятницы ты в замешательстве. Бабушка уже давно просит помощи с огородом, но эгоистичный гедонист внутри просит тусы. Что собираешься делать?;<br>
    Ans:Поехать на дачу.^Event2;<br>
    Ans:Остаться дома и закатить вечеринку.^Event3;<br>
    Break:Break;<br>
    <br>
    Event2:Тяжёлый выбор.;<br>
    Q:На бабушкиной кухне пахнет свежими пирожками и навозом. Ты поел и полон сил. Что дальше?;<br>
    Ans:Пойти полоть картошку.^Event4;<br>
    Ans:Зря я не закатил вечеринку. Пора в деревенский клуб!^Event5;<br>
    Break:Break;<br>
    <br>
    Event3:Враг внутри.;<br>
    F: Ты позвал друзей на вечеринку, но никто не пришёл. Даже твой кот лёг спать пораньше. Скука толкает тебя в холодильник, где припрятана прошлогодняя марка. Забыв о том, что она предназначалась для троих, ешь её целиком. Берёт жёстко - ты видишь Домового, он идёт к тебе. Пора помахать кулаками.;<br>
    Villain:name=Домовой^strength=10^health=25;<br>
    Good:Event8;<br>
    Bad:Event9;<br>
    Break:Break;<br>
    <br>
    Event4:Шулер.;<br>
    Q:Ты вернулся во двор после нескольких часов работы в поле. Руки-крюки, спину тянет. Зычный смех деда привлекает твоё внимание - на завалинке он с друзьями играет в преферанс. Ты вот уже 20 лет мечтаешь научиться в преферанс.;<br>
    Ans:Мечты нужно реализовывать - иду к дедам.^Event6;<br>
    Ans:Азартные игры опасны, лучше завалюсь на печь.^Event7;<br>
    Break:Break;<br>

    Event5:Танцор Рик.;<br>
    F:Ты заходишь в лучших шмотках в деревенский клуб. Местные бросают на тебя недобрый взгляд, но ты - мужчина, поэтому смело идёшь на бар. «Шот водки мне!» - говоришь ты и дрожащей от страха рукой протягиваешь пятисотку бармену. Тот кладёт её в карман, давать сдачу он и не думает. Играет Меладзе, ты готов идти на танцпол. После танца к тебе подходит бойкий парень и харкает прямо на твои белые кеды от Lacoste. Хорошо в этой ситуации только одно - парень в стельку пьян. Стиснув зубы, ты делаешь взмах кулаком…;<br>
    Villain:name=Бойкий парень^strength=3^health=80;<br>
    Good:Event10;<br>
    Bad:Event11;<br>
    Break:Break;<br>
    <br>
    Event6:Фиаско.;<br>
    Lose:Ты остался без мобилы, одежды и квартиры.;<br>
    Break:Break;<br>
    <br>
    Event7:Weekend survivor.;<br>
    Win:Выходные искушали тебя на приключения, но ты предпочёл им родню. Это похвально, но скучно. Главное - ты в полном порядке.;<br>
    Break:Break;<br>
    <br>
    Event8:Игры разума.;<br>
    Win:Ты одолел Домового и пришёл в себя.;<br>
    Break:Break;<br>
    <br>
    Event9:Что происходит.;<br>
    Lose:Домовой победил - ты сошёл с ума.;<br>
    Break:Break;<br>
    <br>
    Event10:Кровавый вкус победы.;<br>
    Win:Бойкий парень некисло тебе накостылял, но видели бы вы его самого.;<br>
    Break:Break;<br>
    <br>
    Event11:Это было жёстко.;<br>
    Lose:Ты очнулся в районной больнице. Тело ломит, голова трещит, зубов не хватает, дух сломлен. Похоже, что нужно было всё-таки копать, а не бухать.;<br>
    Break:Break;<br>
</div>
</body>
</html>
