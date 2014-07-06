package com.chanapps.ranchan.app.models;

import java.util.Calendar;
import java.util.Map;

/**
 * Created by johnarleyburns on 01/07/14.
 */
public class ThreadContentTestHarness {
 
    public static void loadList() {
        synchronized (ThreadContent.mLock) {
            ThreadContent.ITEMS.clear();
            ThreadContent.ITEM_MAP.clear();
            ThreadContent.VIEWED.clear();
            ThreadContent.POSTED.clear();

            Calendar c = Calendar.getInstance();
            c.add(Calendar.HOUR_OF_DAY, -1);
            ThreadContent.addItem(new ThreadItem("7cd91383-6b8b-4432-a5fc-a06d63d561cd", null, "Hey it was my birthday yesterday so I got a new 1tb hard drive which I'm going to dedicate to all my games meaning I need to reinstall my games, plus I got a bunch of money to spend on new games so give me some good recommendation", "moot", c.getTime(), 134134, 665, 396, 0, 187, 32));
            c.add(Calendar.HOUR_OF_DAY, -2);
            ThreadContent.addItem(new ThreadItem("a8364469-3167-4573-b4c3-594d8bcb327e", null, "No waifu thread?  I shall correct that.", null, c.getTime(), 33123, 240, 240, 0, 45, 17));
            c.add(Calendar.HOUR_OF_DAY, -4);
            ThreadContent.addItem(new ThreadItem("286d6398-a9fa-4f19-aa11-c58888d798b7", null, "Ukraine is burning.  Let's celebrate.", null, c.getTime(), 34234, 320, 214, 0, 132, 12));
            c.add(Calendar.HOUR_OF_DAY, -8);
            ThreadContent.addItem(new ThreadItem("c96222a1-da6c-44b0-86c6-5fe17ddb41e8", null, "Porsche thread?  Porsche thread.", "speedygreg", c.getTime(), 454532, 320, 213, 0, 53, 46));
            c.add(Calendar.HOUR_OF_DAY, -16);
            ThreadContent.addItem(new ThreadItem("19f13dc4-91cf-476a-9215-d76fe024e956", null, "hawt bods", null, c.getTime(), 212423, 320, 240, 0, 5, 3));
            c.add(Calendar.HOUR_OF_DAY, -32);
            ThreadContent.addItem(new ThreadItem("8cdd1917-e37b-4385-b777-123c0017e123", null, "black chicks", null, c.getTime(), 235455, 161, 240, ThreadItem.FLAG_ADULT, 78, 52));
            c.add(Calendar.HOUR_OF_DAY, -64);
            ThreadContent.addItem(new ThreadItem("2aeafc10-891b-499d-b806-85f14c72a8af", null, "ISIL ftw!", "derka derka", c.getTime(), 12353, 200, 200, 0, 3, 1));
            c.add(Calendar.HOUR_OF_DAY, -128);
            ThreadContent.addItem(new ThreadItem("ae748873-2ab2-4efe-a546-9024ccf760af", null, "Yotsuba goes to the beach, the latest manga is out, I'm dumping all 200 pages here... enjoy!", null, c.getTime(), 1343234, 165, 240, 0, 85, 83));
            c.add(Calendar.HOUR_OF_DAY, -512);
            ThreadContent.addItem(new ThreadItem("a2b18964-8b93-4074-a3a0-f56dfa180d68", null, "Please post in my thread.", null, c.getTime(), 23423, 528, 396, 0, 0, 0));
            c.add(Calendar.HOUR_OF_DAY, -1024);
            ThreadContent.addItem(new ThreadItem("f2b46979-13f5-4357-8379-bc4d4fc37a2e", null, "Let's talk about meditation.  Lately I've been eating only fruits and vegetables and meditating, is this going to give me superpowers?", null, c.getTime(), 0, 0, 0, 0, 38, 0));
            c.add(Calendar.HOUR_OF_DAY, -10000);
            ThreadContent.addItem(new ThreadItem("f2b46979-13f5-4357-8379-bc4d4fc38a2e", null, "Bikini thread", null, c.getTime(), 234892, 160, 240, ThreadItem.FLAG_ADULT, 167, 150));

            ThreadContent.addViewed(ThreadContent.getItem(1).id);
            ThreadContent.addViewed(ThreadContent.getItem(2).id);
            ThreadContent.addViewed(ThreadContent.getItem(9).id);
            ThreadContent.addViewed(ThreadContent.getItem(10).id);

            ThreadContent.addPosted(ThreadContent.getItem(1).id);
            ThreadContent.addPosted(ThreadContent.getItem(2).id);
        }
    }

    public static void loadDetail() {
        synchronized (ThreadContent.mLock) {
            ThreadContent.DETAIL_ITEMS.clear();
            ThreadContent.DETAIL_ITEM_MAP.clear();
            Calendar c = Calendar.getInstance();
            c.add(Calendar.HOUR_OF_DAY, -1);
            ThreadContent.addDetailItem(new ThreadItem("7cd91383-6b8b-4432-a5fc-a06d63d561cd", null, "Hey it was my birthday yesterday so I got a new 1tb hard drive which I'm going to dedicate to all my games meaning I need to reinstall my games, plus I got a bunch of money to spend on new games so give me some good recommendation", "moot", c.getTime(), 65343, 665, 396, 0, 187, 32));
            c.add(Calendar.MINUTE, 1);
            ThreadContent.addDetailItem(new ThreadItem("a19f590a-eb73-495d-8a6b-c882c106e796", "7cd91383-6b8b-4432-a5fc-a06d63d561cd", "help the poor beggers", null, c.getTime(), 0, 0, 0, 0, 0, 0));
            c.add(Calendar.MINUTE, 2);
            ThreadContent.addDetailItem(new ThreadItem("cc6795e3-fa9d-4cb0-9cbc-a3766cd3ccaf", "7cd91383-6b8b-4432-a5fc-a06d63d561cd", ">help the poor beggers\nI would but I have done that stuff before and I just feel like no one is grateful enough, sorry", null, c.getTime(), 0, 0, 0, 0, 0, 0));
            c.add(Calendar.MINUTE, 2);
            ThreadContent.addDetailItem(new ThreadItem("8b67d0c3-d7f0-4c0c-b6f9-66d5d3e3081b", "7cd91383-6b8b-4432-a5fc-a06d63d561cd", "Isn't Sven 1/10", "Captain Flavius", c.getTime(), 12324, 192, 240, 0, 0, 0));
            c.add(Calendar.MINUTE, 1);
            ThreadContent.addDetailItem(new ThreadItem("7914c43c-2147-46c8-adda-ab21466b9f86", "7cd91383-6b8b-4432-a5fc-a06d63d561cd", "Rate my favorite dota!", "dotaboy", c.getTime(), 3452234, 3072, 2304, 0, 0, 0));
            c.add(Calendar.MINUTE, 5);
            ThreadContent.addDetailItem(new ThreadItem("91cd097e-9953-47ae-aec0-cd410bfb6c33", "7cd91383-6b8b-4432-a5fc-a06d63d561cd", ">Rate my favorite dota!\ncan i get these stuff for free or do i have to pay for it?  i new into dota 2", null, c.getTime(), 0, 0, 0, 0, 0, 0));
            c.add(Calendar.MINUTE, 1);
            ThreadContent.addDetailItem(new ThreadItem("ccde0817-5d54-4e22-8c39-6fc10b6f24f2", "7cd91383-6b8b-4432-a5fc-a06d63d561cd", ">can i get these stuff for free...\nJust play for a while.  You'll get some drops.\n\nUnless you want that immortal, you'll need to pay", null, c.getTime(), 0, 0, 0, 0, 0, 0));
            c.add(Calendar.MINUTE, 1);
            ThreadContent.addDetailItem(new ThreadItem("8f7fbea3-8273-42c7-895c-40602780e0a6", "7cd91383-6b8b-4432-a5fc-a06d63d561cd", ">Playing DOTA instead of a superior MOBA\nhttp://4chan.org\nFaggot Tier/10", "antifacrusader", c.getTime(), 0, 0, 0, 0, 0, 0));
            c.add(Calendar.MINUTE, 1);
            ThreadContent.addDetailItem(new ThreadItem("520dc9bb-ad7b-4570-a7e6-3d49ae9db277", "7cd91383-6b8b-4432-a5fc-a06d63d561cd", "500+ games ALL anti-mage.  Get. The. Fuck. On my level you fucking n00bz.", null, c.getTime(), 0, 0, 0, 0, 0, 0));
            c.add(Calendar.MINUTE, 3);
            ThreadContent.addDetailItem(new ThreadItem("c04cbb80-9c55-4096-9a56-788e276835cd", "7cd91383-6b8b-4432-a5fc-a06d63d561cd", ">Faggot Tier/10\nPlaying MOBA at all.  Stop acting elitist over such a shitty type of game.", null, c.getTime(), 0, 0, 0, 0, 0, 0));
            c.add(Calendar.MINUTE, 2);
            ThreadContent.addDetailItem(new ThreadItem("6cef17c6-9719-486a-8535-97f2e4921122", "7cd91383-6b8b-4432-a5fc-a06d63d561cd", null, null, c.getTime(), 55832, 307, 578, 0, 0, 0));
            ThreadContent.addPosted(ThreadContent.getDetailItem(1).id);
            ThreadContent.addPosted(ThreadContent.getDetailItem(10).id);
        }
    }

    static final Map<String, String> TEST_THUMBS = ThreadItem.build(

            "7cd91383-6b8b-4432-a5fc-a06d63d561cd", // 665x396
            "https://upload.wikimedia.org/wikipedia/commons/5/54/Solitude_%281894076214%29.jpg",

            "a8364469-3167-4573-b4c3-594d8bcb327e", //240x240
            "https://upload.wikimedia.org/wikipedia/commons/thumb/5/51/Figure_in_Manga_style_variation_1.png/240px-Figure_in_Manga_style_variation_1.png",

            "286d6398-a9fa-4f19-aa11-c58888d798b7", // 320x214
            "https://upload.wikimedia.org/wikipedia/commons/thumb/8/80/Protesters_on_the_barricades%2C_seen_through_the_lights_of_fire_built._Euromaidan_Protests.jpg/320px-Protesters_on_the_barricades%2C_seen_through_the_lights_of_fire_built._Euromaidan_Protests.jpg",

            "c96222a1-da6c-44b0-86c6-5fe17ddb41e8", // 320x213
            "https://upload.wikimedia.org/wikipedia/commons/thumb/a/ab/Porsche.jpg/320px-Porsche.jpg",

            "19f13dc4-91cf-476a-9215-d76fe024e956", // 320x240
            "https://upload.wikimedia.org/wikipedia/commons/thumb/e/ef/Tremendo_gordo.jpg/320px-Tremendo_gordo.jpg",

            "8cdd1917-e37b-4385-b777-123c0017e123", // 161x240
            "https://upload.wikimedia.org/wikipedia/commons/thumb/7/76/Black_woman_dancing_%281634389290%29.jpg/161px-Black_woman_dancing_%281634389290%29.jpg",

            "2aeafc10-891b-499d-b806-85f14c72a8af", // 200x200
            "https://upload.wikimedia.org/wikipedia/commons/thumb/7/7c/Flag_of_Islamic_State_of_Iraq.svg/200px-Flag_of_Islamic_State_of_Iraq.svg.png",

            "ae748873-2ab2-4efe-a546-9024ccf760af", // 165x240
            "https://upload.wikimedia.org/wikipedia/commons/thumb/d/df/Anime_girl_at_beach.jpg/165px-Anime_girl_at_beach.jpg",

            "a2b18964-8b93-4074-a3a0-f56dfa180d68", // 528x396
            "https://upload.wikimedia.org/wikipedia/commons/3/3c/Graffiti_Here_Please.jpg",

            "f2b46979-13f5-4357-8379-bc4d4fc38a2e", // 160x240
            "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b2/Topless_woman_walking_on_Coral_Beach%2C_Jamaica.jpg/160px-Topless_woman_walking_on_Coral_Beach%2C_Jamaica.jpg",

            "8b67d0c3-d7f0-4c0c-b6f9-66d5d3e3081b", // 192x240
            "https://upload.wikimedia.org/wikipedia/commons/thumb/f/fc/Sven_Melander.jpg/192px-Sven_Melander.jpg",

            "7914c43c-2147-46c8-adda-ab21466b9f86", // 320x240
            "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4b/Dota-juku_01.JPG/320px-Dota-juku_01.JPG",

            "6cef17c6-9719-486a-8535-97f2e4921122", // 307x578
            "https://upload.wikimedia.org/wikipedia/commons/c/c3/Chavosh.jpg"

    );

    static final Map<String, String> TEST_IMAGES = ThreadItem.build(

            "7914c43c-2147-46c8-adda-ab21466b9f86", // 3072x2304
            "https://upload.wikimedia.org/wikipedia/commons/4/4b/Dota-juku_01.JPG"

    );

}
