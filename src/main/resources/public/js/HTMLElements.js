function getNav() {
    const nav = ' <ul> ' +
        '<a href="../index.html"> <li>Home</li></a>' +
        '<a href="../html/Games.html"><li>Games</li></a> ' +
        '<a href="../html/Rules.html"> <li>Rules</li> </a>' +
        '<a href="../html/Staff.html"><li>Staff</li></a><a href="../html/About.html"><li>About</li> </a>' +
        '<a href="../html/Apply.html"><li>Apply!</li></a>' +
        '</ul>';
    return nav;
}
function getSideBar() {
    const sideBar = " <div class='login-sector'>"+
                               "            <div class='sec'>"+
                               "    <h1>Apply Here!</h1>"+
                               "                <a class='log-button first-log' href='html/Apply.html'>Apply</a>"+
                               "            </div>"+
                               "            <div class='sec' id='logins'>"+
                               ""+
                               "            </div>"+
                               "            <h1>Socialmedia</h1>"+
                               "            <div class='icons'>"+
                               "                <a target='_blank' href='https://discord.gg/4JwPCxN'><img class='icon'"+
                               "    src='img/Socialmedia/discord_101785.png'></a>"+
                               "                <a target='_blank' href='https://www.youtube.com/channel/UCigGxg0kOHJnM_K_kRdYAoA'><img class='icon'"+
                               "    src='img/Socialmedia/youtube_101764.png'></a>"+
                               "                <a target='_blank' href='mailto:chestly.questcraft@gmail.com'><img class='icon'"+
                               "    src='img/Socialmedia/1486564396-mail_81524.png'></a>"+
                               "                <a target='_blank' href='https://www.planetminecraft.com/server/questcraft-1-14-3/'><img class='icon'"+
                               "    src='img/Socialmedia/vote_pmc.png'></a>"+
                               "            </div>"+
                               ""+
                               "        </div>"
    return sideBar;
}