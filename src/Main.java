import DataLayer.GameDAO;
import DataLayer.UserDAO;
import Model.Bet;
import Model.Better;
import Model.Game;
import Model.RasBetFacade;
import UI.RasBetUI;
import UI.TextUI;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class Main {
    
    public static void main(String[] args) {
        RasBetFacade model = new RasBetFacade();
        // IR BUSCAR OS DADOS Á API
        /*
        System.out.println("Trying to retrive server data");
        String out = model.getServerData();
        if(out == null) System.out.println("[Network Error] Can't fetch data from \"http://ucras.di.uminho.pt/v1/games/\"");
        else System.out.println(out);
        */
        List<Game> g = model.parseJson("[{\"id\":\"09ed0664c62c7641dd7d9d8be41f7992\",\"awayTeam\":\"Sporting Lisbon\",\"bookmakers\":[{\"key\":\"mybookieag\",\"lastUpdate\":\"2022-10-29T21:27:37Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Arouca\",\"price\":1.35},{\"name\":\"Sporting Lisbon\",\"price\":15.5},{\"name\":\"Draw\",\"price\":3.5}]}]},{\"key\":\"betclic\",\"lastUpdate\":\"2022-10-29T21:21:15Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Arouca\",\"price\":1.01},{\"name\":\"Sporting Lisbon\",\"price\":100},{\"name\":\"Draw\",\"price\":13}]}]},{\"key\":\"betsson\",\"lastUpdate\":\"2022-10-29T21:21:17Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Arouca\",\"price\":1.01},{\"name\":\"Sporting Lisbon\",\"price\":201},{\"name\":\"Draw\",\"price\":15}]}]},{\"key\":\"williamhill\",\"lastUpdate\":\"2022-10-29T21:29:34Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Arouca\",\"price\":7},{\"name\":\"Sporting Lisbon\",\"price\":1.44},{\"name\":\"Draw\",\"price\":4.2}]}]},{\"key\":\"pinnacle\",\"lastUpdate\":\"2022-10-29T21:18:51Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Arouca\",\"price\":1.19},{\"name\":\"Sporting Lisbon\",\"price\":44.39},{\"name\":\"Draw\",\"price\":5.51}]}]},{\"key\":\"betfair\",\"lastUpdate\":\"2022-10-29T21:19:40Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Arouca\",\"price\":1.16},{\"name\":\"Sporting Lisbon\",\"price\":100},{\"name\":\"Draw\",\"price\":6.8}]},{\"key\":\"h2h_lay\",\"outcomes\":[{\"name\":\"Arouca\",\"price\":1.18},{\"name\":\"Sporting Lisbon\",\"price\":1000},{\"name\":\"Draw\",\"price\":7.8}]}]},{\"key\":\"unibet_eu\",\"lastUpdate\":\"2022-10-29T21:18:53Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Arouca\",\"price\":1.05},{\"name\":\"Sporting Lisbon\",\"price\":276},{\"name\":\"Draw\",\"price\":12}]}]}],\"commenceTime\":\"2022-10-29T19:29:52.000Z\",\"completed\":true,\"homeTeam\":\"Arouca\",\"scores\":\"1x0\"},{\"id\":\"9ce21745e088977fcc44aa2a66e739ee\",\"awayTeam\":\"Vizela\",\"bookmakers\":[{\"key\":\"betclic\",\"lastUpdate\":\"2022-10-30T17:29:43Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Boavista Porto\",\"price\":14},{\"name\":\"Vizela\",\"price\":30},{\"name\":\"Draw\",\"price\":1.03}]}]},{\"key\":\"pinnacle\",\"lastUpdate\":\"2022-10-30T17:29:42Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Boavista Porto\",\"price\":5.62},{\"name\":\"Vizela\",\"price\":14.15},{\"name\":\"Draw\",\"price\":1.21}]}]},{\"key\":\"betsson\",\"lastUpdate\":\"2022-10-30T17:29:45Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Boavista Porto\",\"price\":15},{\"name\":\"Vizela\",\"price\":36},{\"name\":\"Draw\",\"price\":1.03}]}]},{\"key\":\"williamhill\",\"lastUpdate\":\"2022-10-30T17:29:41Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Boavista Porto\",\"price\":2.5},{\"name\":\"Vizela\",\"price\":2.88},{\"name\":\"Draw\",\"price\":3.1}]}]},{\"key\":\"unibet_eu\",\"lastUpdate\":\"2022-10-30T17:27:55Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Boavista Porto\",\"price\":12.5},{\"name\":\"Vizela\",\"price\":30},{\"name\":\"Draw\",\"price\":1.07}]}]},{\"key\":\"betfair\",\"lastUpdate\":\"2022-10-30T17:29:43Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Boavista Porto\",\"price\":7.2},{\"name\":\"Vizela\",\"price\":34},{\"name\":\"Draw\",\"price\":1.15}]},{\"key\":\"h2h_lay\",\"outcomes\":[{\"name\":\"Boavista Porto\",\"price\":9.2},{\"name\":\"Vizela\",\"price\":350},{\"name\":\"Draw\",\"price\":1.18}]}]}],\"commenceTime\":\"2022-10-30T15:31:59.000Z\",\"completed\":true,\"homeTeam\":\"Boavista Porto\",\"scores\":\"2x2\"},{\"id\":\"2dc719929d4d4332adbd0ec53bff8b39\",\"awayTeam\":\"Estoril\",\"bookmakers\":[{\"key\":\"betclic\",\"lastUpdate\":\"2022-10-30T17:29:43Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Estoril\",\"price\":30},{\"name\":\"Portimonense\",\"price\":15},{\"name\":\"Draw\",\"price\":1.02}]}]},{\"key\":\"mybookieag\",\"lastUpdate\":\"2022-10-30T17:29:44Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Estoril\",\"price\":1.35},{\"name\":\"Portimonense\",\"price\":14.5},{\"name\":\"Draw\",\"price\":3.4}]}]},{\"key\":\"betsson\",\"lastUpdate\":\"2022-10-30T17:24:24Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Estoril\",\"price\":17},{\"name\":\"Portimonense\",\"price\":5.5},{\"name\":\"Draw\",\"price\":1.22}]}]},{\"key\":\"williamhill\",\"lastUpdate\":\"2022-10-30T17:29:41Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Estoril\",\"price\":3},{\"name\":\"Portimonense\",\"price\":2.45},{\"name\":\"Draw\",\"price\":3.1}]}]},{\"key\":\"unibet_eu\",\"lastUpdate\":\"2022-10-30T17:27:55Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Estoril\",\"price\":30},{\"name\":\"Portimonense\",\"price\":11},{\"name\":\"Draw\",\"price\":1.08}]}]},{\"key\":\"betfair\",\"lastUpdate\":\"2022-10-30T17:29:43Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Estoril\",\"price\":11.5},{\"name\":\"Portimonense\",\"price\":12},{\"name\":\"Draw\",\"price\":1.09}]},{\"key\":\"h2h_lay\",\"outcomes\":[{\"name\":\"Estoril\",\"price\":350},{\"name\":\"Portimonense\",\"price\":1000},{\"name\":\"Draw\",\"price\":1.1}]}]},{\"key\":\"pinnacle\",\"lastUpdate\":\"2022-10-30T17:29:42Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Estoril\",\"price\":20.7},{\"name\":\"Portimonense\",\"price\":6.59},{\"name\":\"Draw\",\"price\":1.15}]}]}],\"commenceTime\":\"2022-10-30T15:31:47.000Z\",\"completed\":true,\"homeTeam\":\"Portimonense\",\"scores\":\"1x1\"},{\"id\":\"c56ff51fa20b6a16cfbb15002aabcbfe\",\"awayTeam\":\"Rio Ave FC\",\"bookmakers\":[{\"key\":\"betclic\",\"lastUpdate\":\"2022-10-30T19:59:45Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Casa Pia\",\"price\":1},{\"name\":\"Rio Ave FC\",\"price\":65},{\"name\":\"Draw\",\"price\":15}]}]},{\"key\":\"mybookieag\",\"lastUpdate\":\"2022-10-30T19:54:33Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Casa Pia\",\"price\":1.15},{\"name\":\"Rio Ave FC\",\"price\":32},{\"name\":\"Draw\",\"price\":5}]}]},{\"key\":\"betsson\",\"lastUpdate\":\"2022-10-30T19:59:47Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Casa Pia\",\"price\":1},{\"name\":\"Rio Ave FC\",\"price\":101},{\"name\":\"Draw\",\"price\":19}]}]},{\"key\":\"williamhill\",\"lastUpdate\":\"2022-10-30T19:59:43Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Casa Pia\",\"price\":2},{\"name\":\"Rio Ave FC\",\"price\":3.9},{\"name\":\"Draw\",\"price\":3.2}]}]},{\"key\":\"betfair\",\"lastUpdate\":\"2022-10-30T19:53:08Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Casa Pia\",\"price\":1.11},{\"name\":\"Rio Ave FC\",\"price\":46},{\"name\":\"Draw\",\"price\":9.2}]},{\"key\":\"h2h_lay\",\"outcomes\":[{\"name\":\"Casa Pia\",\"price\":1.13},{\"name\":\"Rio Ave FC\",\"price\":1000},{\"name\":\"Draw\",\"price\":10.5}]}]},{\"key\":\"pinnacle\",\"lastUpdate\":\"2022-10-30T19:54:32Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Casa Pia\",\"price\":1.19},{\"name\":\"Rio Ave FC\",\"price\":39.73},{\"name\":\"Draw\",\"price\":5.81}]}]}],\"commenceTime\":\"2022-10-30T18:03:35.000Z\",\"completed\":true,\"homeTeam\":\"Casa Pia\",\"scores\":\"1x0\"},{\"id\":\"e8ab481bcf9d2917419bfb34ac0d0da7\",\"awayTeam\":\"Braga\",\"bookmakers\":[{\"key\":\"pinnacle\",\"lastUpdate\":\"2022-10-30T21:44:12Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Braga\",\"price\":1.79},{\"name\":\"Gil Vicente\",\"price\":7.39},{\"name\":\"Draw\",\"price\":2.87}]}]},{\"key\":\"betclic\",\"lastUpdate\":\"2022-10-30T21:44:13Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Braga\",\"price\":1.85},{\"name\":\"Gil Vicente\",\"price\":6.75},{\"name\":\"Draw\",\"price\":2.55}]}]},{\"key\":\"mybookieag\",\"lastUpdate\":\"2022-10-30T21:44:13Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Braga\",\"price\":1.65},{\"name\":\"Gil Vicente\",\"price\":6.5},{\"name\":\"Draw\",\"price\":2.95}]}]},{\"key\":\"betsson\",\"lastUpdate\":\"2022-10-30T21:44:15Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Braga\",\"price\":1.87},{\"name\":\"Gil Vicente\",\"price\":7},{\"name\":\"Draw\",\"price\":2.6}]}]},{\"key\":\"williamhill\",\"lastUpdate\":\"2022-10-30T21:44:11Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Braga\",\"price\":1.67},{\"name\":\"Gil Vicente\",\"price\":5},{\"name\":\"Draw\",\"price\":3.7}]}]},{\"key\":\"unibet_eu\",\"lastUpdate\":\"2022-10-30T21:44:15Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Braga\",\"price\":1.85},{\"name\":\"Gil Vicente\",\"price\":6.75},{\"name\":\"Draw\",\"price\":2.65}]}]},{\"key\":\"betfair\",\"lastUpdate\":\"2022-10-30T21:44:14Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Braga\",\"price\":1.87},{\"name\":\"Gil Vicente\",\"price\":7.8},{\"name\":\"Draw\",\"price\":2.88}]},{\"key\":\"h2h_lay\",\"outcomes\":[{\"name\":\"Braga\",\"price\":1.89},{\"name\":\"Gil Vicente\",\"price\":8.4},{\"name\":\"Draw\",\"price\":2.96}]}]},{\"key\":\"matchbook\",\"lastUpdate\":\"2022-10-30T21:44:12Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Braga\",\"price\":1.86},{\"name\":\"Gil Vicente\",\"price\":7},{\"name\":\"Draw\",\"price\":2.78}]},{\"key\":\"h2h_lay\",\"outcomes\":[{\"name\":\"Braga\",\"price\":1.98},{\"name\":\"Gil Vicente\",\"price\":8.6},{\"name\":\"Draw\",\"price\":2.96}]}]}],\"commenceTime\":\"2022-10-30T20:29:57.000Z\",\"completed\":true,\"homeTeam\":\"Gil Vicente\",\"scores\":\"0x1\"},{\"id\":\"d412d9b63fc1ee28b5c7ed331d0127ce\",\"awayTeam\":\"Famalicão\",\"bookmakers\":[{\"key\":\"mybookieag\",\"lastUpdate\":\"2022-10-31T21:44:30Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Famalicão\",\"price\":12.5},{\"name\":\"Vitória SC\",\"price\":1.32},{\"name\":\"Draw\",\"price\":3.8}]}]},{\"key\":\"pinnacle\",\"lastUpdate\":\"2022-10-31T21:44:29Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Famalicão\",\"price\":83.92},{\"name\":\"Vitória SC\",\"price\":1.04},{\"name\":\"Draw\",\"price\":18.66}]}]},{\"key\":\"betclic\",\"lastUpdate\":\"2022-10-31T21:40:01Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Famalicão\",\"price\":80},{\"name\":\"Vitória SC\",\"price\":1},{\"name\":\"Draw\",\"price\":17}]}]},{\"key\":\"betsson\",\"lastUpdate\":\"2022-10-31T21:44:32Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Famalicão\",\"price\":46},{\"name\":\"Vitória SC\",\"price\":1},{\"name\":\"Draw\",\"price\":23}]}]},{\"key\":\"williamhill\",\"lastUpdate\":\"2022-10-31T21:43:37Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Famalicão\",\"price\":3.3},{\"name\":\"Vitória SC\",\"price\":2.25},{\"name\":\"Draw\",\"price\":3.1}]}]},{\"key\":\"betfair\",\"lastUpdate\":\"2022-10-31T21:40:02Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Famalicão\",\"price\":120},{\"name\":\"Vitória SC\",\"price\":1.06},{\"name\":\"Draw\",\"price\":18.5}]},{\"key\":\"h2h_lay\",\"outcomes\":[{\"name\":\"Famalicão\",\"price\":180},{\"name\":\"Vitória SC\",\"price\":1.07},{\"name\":\"Draw\",\"price\":21}]}]}],\"commenceTime\":\"2022-10-31T20:16:08.000Z\",\"completed\":true,\"homeTeam\":\"Vitória SC\",\"scores\":\"3x2\"},{\"id\":\"538136a794711c8c9bc24b48353c396c\",\"awayTeam\":\"Portimonense\",\"bookmakers\":[{\"key\":\"marathonbet\",\"lastUpdate\":\"2022-11-01T11:54:09Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Gil Vicente\",\"price\":2.11},{\"name\":\"Portimonense\",\"price\":3.36},{\"name\":\"Draw\",\"price\":3.45}]}]},{\"key\":\"betclic\",\"lastUpdate\":\"2022-11-01T11:58:49Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Gil Vicente\",\"price\":2.14},{\"name\":\"Portimonense\",\"price\":3.38},{\"name\":\"Draw\",\"price\":3.48}]}]},{\"key\":\"betsson\",\"lastUpdate\":\"2022-11-01T11:59:32Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Gil Vicente\",\"price\":2.1},{\"name\":\"Portimonense\",\"price\":3.4},{\"name\":\"Draw\",\"price\":3.4}]}]},{\"key\":\"onexbet\",\"lastUpdate\":\"2022-11-01T11:53:53Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Gil Vicente\",\"price\":2.13},{\"name\":\"Portimonense\",\"price\":3.36},{\"name\":\"Draw\",\"price\":3.44}]}]},{\"key\":\"mybookieag\",\"lastUpdate\":\"2022-11-01T11:55:46Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Gil Vicente\",\"price\":2.11},{\"name\":\"Portimonense\",\"price\":3.3},{\"name\":\"Draw\",\"price\":3.4}]}]},{\"key\":\"williamhill\",\"lastUpdate\":\"2022-11-01T11:54:10Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Gil Vicente\",\"price\":2.1},{\"name\":\"Portimonense\",\"price\":3.4},{\"name\":\"Draw\",\"price\":3.3}]}]},{\"key\":\"betfair\",\"lastUpdate\":\"2022-11-01T11:56:34Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Gil Vicente\",\"price\":2.18},{\"name\":\"Portimonense\",\"price\":3.2},{\"name\":\"Draw\",\"price\":3.4}]},{\"key\":\"h2h_lay\",\"outcomes\":[{\"name\":\"Gil Vicente\",\"price\":2.34},{\"name\":\"Portimonense\",\"price\":4.1},{\"name\":\"Draw\",\"price\":3.8}]}]},{\"key\":\"pinnacle\",\"lastUpdate\":\"2022-11-01T11:58:49Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Gil Vicente\",\"price\":2.18},{\"name\":\"Portimonense\",\"price\":3.36},{\"name\":\"Draw\",\"price\":3.59}]}]},{\"key\":\"betonlineag\",\"lastUpdate\":\"2022-11-01T11:54:11Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Gil Vicente\",\"price\":2.11},{\"name\":\"Portimonense\",\"price\":3.2},{\"name\":\"Draw\",\"price\":3.45}]}]},{\"key\":\"unibet_eu\",\"lastUpdate\":\"2022-11-01T11:59:54Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Gil Vicente\",\"price\":2.02},{\"name\":\"Portimonense\",\"price\":3.5},{\"name\":\"Draw\",\"price\":3.4}]}]},{\"key\":\"sport888\",\"lastUpdate\":\"2022-11-01T11:54:10Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Gil Vicente\",\"price\":1.98},{\"name\":\"Portimonense\",\"price\":3.4},{\"name\":\"Draw\",\"price\":3.35}]}]},{\"key\":\"intertops\",\"lastUpdate\":\"2022-11-01T11:55:53Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Gil Vicente\",\"price\":2.1},{\"name\":\"Portimonense\",\"price\":3.2},{\"name\":\"Draw\",\"price\":3.4}]}]}],\"commenceTime\":\"2022-11-04T20:15:00.000Z\",\"completed\":false,\"homeTeam\":\"Gil Vicente\",\"scores\":null},{\"id\":\"be09fd32746c6c2f11a64157039dc992\",\"awayTeam\":\"Arouca\",\"bookmakers\":[{\"key\":\"betsson\",\"lastUpdate\":\"2022-11-01T11:59:32Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Arouca\",\"price\":4},{\"name\":\"Vizela\",\"price\":1.9},{\"name\":\"Draw\",\"price\":3.5}]}]},{\"key\":\"marathonbet\",\"lastUpdate\":\"2022-11-01T11:54:09Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Arouca\",\"price\":4.1},{\"name\":\"Vizela\",\"price\":1.86},{\"name\":\"Draw\",\"price\":3.58}]}]},{\"key\":\"onexbet\",\"lastUpdate\":\"2022-11-01T11:53:53Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Arouca\",\"price\":4.06},{\"name\":\"Vizela\",\"price\":1.88},{\"name\":\"Draw\",\"price\":3.58}]}]},{\"key\":\"betclic\",\"lastUpdate\":\"2022-11-01T11:58:49Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Arouca\",\"price\":4.13},{\"name\":\"Vizela\",\"price\":1.95},{\"name\":\"Draw\",\"price\":3.4}]}]},{\"key\":\"mybookieag\",\"lastUpdate\":\"2022-11-01T11:55:46Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Arouca\",\"price\":4},{\"name\":\"Vizela\",\"price\":1.93},{\"name\":\"Draw\",\"price\":3.35}]}]},{\"key\":\"williamhill\",\"lastUpdate\":\"2022-11-01T11:54:10Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Arouca\",\"price\":4},{\"name\":\"Vizela\",\"price\":1.91},{\"name\":\"Draw\",\"price\":3.4}]}]},{\"key\":\"betfair\",\"lastUpdate\":\"2022-11-01T11:56:34Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Arouca\",\"price\":4.2},{\"name\":\"Vizela\",\"price\":1.94},{\"name\":\"Draw\",\"price\":3.4}]},{\"key\":\"h2h_lay\",\"outcomes\":[{\"name\":\"Arouca\",\"price\":5.3},{\"name\":\"Vizela\",\"price\":2.08},{\"name\":\"Draw\",\"price\":4.1}]}]},{\"key\":\"pinnacle\",\"lastUpdate\":\"2022-11-01T11:58:49Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Arouca\",\"price\":4.37},{\"name\":\"Vizela\",\"price\":1.95},{\"name\":\"Draw\",\"price\":3.4}]}]},{\"key\":\"intertops\",\"lastUpdate\":\"2022-11-01T11:55:53Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Arouca\",\"price\":4.1},{\"name\":\"Vizela\",\"price\":1.9},{\"name\":\"Draw\",\"price\":3.25}]}]},{\"key\":\"unibet_eu\",\"lastUpdate\":\"2022-11-01T11:59:54Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Arouca\",\"price\":4.2},{\"name\":\"Vizela\",\"price\":1.89},{\"name\":\"Draw\",\"price\":3.3}]}]},{\"key\":\"sport888\",\"lastUpdate\":\"2022-11-01T11:54:10Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Arouca\",\"price\":4.1},{\"name\":\"Vizela\",\"price\":1.86},{\"name\":\"Draw\",\"price\":3.2}]}]},{\"key\":\"betonlineag\",\"lastUpdate\":\"2022-11-01T11:54:11Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Arouca\",\"price\":3.8},{\"name\":\"Vizela\",\"price\":1.91},{\"name\":\"Draw\",\"price\":3.35}]}]}],\"commenceTime\":\"2022-11-05T15:30:00.000Z\",\"completed\":false,\"homeTeam\":\"Vizela\",\"scores\":null},{\"id\":\"c8d53fadc30913fa823bea3999311b81\",\"awayTeam\":\"Pacos de Ferreira\",\"bookmakers\":[{\"key\":\"betsson\",\"lastUpdate\":\"2022-11-01T11:59:32Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"FC Porto\",\"price\":1.1},{\"name\":\"Pacos de Ferreira\",\"price\":26},{\"name\":\"Draw\",\"price\":9}]}]},{\"key\":\"marathonbet\",\"lastUpdate\":\"2022-11-01T11:54:09Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"FC Porto\",\"price\":1.1},{\"name\":\"Pacos de Ferreira\",\"price\":23},{\"name\":\"Draw\",\"price\":9.3}]}]},{\"key\":\"onexbet\",\"lastUpdate\":\"2022-11-01T11:53:53Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"FC Porto\",\"price\":1.1},{\"name\":\"Pacos de Ferreira\",\"price\":25},{\"name\":\"Draw\",\"price\":9.3}]}]},{\"key\":\"betclic\",\"lastUpdate\":\"2022-11-01T11:58:49Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"FC Porto\",\"price\":1.11},{\"name\":\"Pacos de Ferreira\",\"price\":24.5},{\"name\":\"Draw\",\"price\":9.35}]}]},{\"key\":\"mybookieag\",\"lastUpdate\":\"2022-11-01T11:55:46Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"FC Porto\",\"price\":1.1},{\"name\":\"Pacos de Ferreira\",\"price\":22},{\"name\":\"Draw\",\"price\":8.6}]}]},{\"key\":\"williamhill\",\"lastUpdate\":\"2022-11-01T11:54:10Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"FC Porto\",\"price\":1.1},{\"name\":\"Pacos de Ferreira\",\"price\":29},{\"name\":\"Draw\",\"price\":8}]}]},{\"key\":\"pinnacle\",\"lastUpdate\":\"2022-11-01T11:58:49Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"FC Porto\",\"price\":1.12},{\"name\":\"Pacos de Ferreira\",\"price\":24.74},{\"name\":\"Draw\",\"price\":9.9}]}]},{\"key\":\"betfair\",\"lastUpdate\":\"2022-11-01T11:56:34Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"FC Porto\",\"price\":1.11},{\"name\":\"Pacos de Ferreira\",\"price\":1.04},{\"name\":\"Draw\",\"price\":9.4}]},{\"key\":\"h2h_lay\",\"outcomes\":[{\"name\":\"FC Porto\",\"price\":1.15},{\"name\":\"Pacos de Ferreira\",\"price\":42},{\"name\":\"Draw\",\"price\":950}]}]},{\"key\":\"intertops\",\"lastUpdate\":\"2022-11-01T11:55:53Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"FC Porto\",\"price\":1.08},{\"name\":\"Pacos de Ferreira\",\"price\":21},{\"name\":\"Draw\",\"price\":8.5}]}]},{\"key\":\"unibet_eu\",\"lastUpdate\":\"2022-11-01T11:59:54Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"FC Porto\",\"price\":1.1},{\"name\":\"Pacos de Ferreira\",\"price\":19},{\"name\":\"Draw\",\"price\":9}]}]},{\"key\":\"sport888\",\"lastUpdate\":\"2022-11-01T11:54:10Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"FC Porto\",\"price\":1.08},{\"name\":\"Pacos de Ferreira\",\"price\":18},{\"name\":\"Draw\",\"price\":8.5}]}]},{\"key\":\"betonlineag\",\"lastUpdate\":\"2022-11-01T11:54:11Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"FC Porto\",\"price\":1.09},{\"name\":\"Pacos de Ferreira\",\"price\":20},{\"name\":\"Draw\",\"price\":8.9}]}]}],\"commenceTime\":\"2022-11-05T18:00:00.000Z\",\"completed\":false,\"homeTeam\":\"FC Porto\",\"scores\":null},{\"id\":\"6ae5f22198593f180590bf8161bbf910\",\"awayTeam\":\"Vitória SC\",\"bookmakers\":[{\"key\":\"mybookieag\",\"lastUpdate\":\"2022-11-01T11:55:46Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Sporting Lisbon\",\"price\":1.35},{\"name\":\"Vitória SC\",\"price\":8.2},{\"name\":\"Draw\",\"price\":4.9}]}]},{\"key\":\"betsson\",\"lastUpdate\":\"2022-11-01T11:59:32Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Sporting Lisbon\",\"price\":1.35},{\"name\":\"Vitória SC\",\"price\":8.5},{\"name\":\"Draw\",\"price\":5}]}]},{\"key\":\"betclic\",\"lastUpdate\":\"2022-11-01T11:58:49Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Sporting Lisbon\",\"price\":1.36},{\"name\":\"Vitória SC\",\"price\":8.45},{\"name\":\"Draw\",\"price\":5.05}]}]},{\"key\":\"marathonbet\",\"lastUpdate\":\"2022-11-01T11:54:09Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Sporting Lisbon\",\"price\":1.34},{\"name\":\"Vitória SC\",\"price\":8.9},{\"name\":\"Draw\",\"price\":4.95}]}]},{\"key\":\"williamhill\",\"lastUpdate\":\"2022-11-01T11:54:10Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Sporting Lisbon\",\"price\":1.33},{\"name\":\"Vitória SC\",\"price\":9.5},{\"name\":\"Draw\",\"price\":4.6}]}]},{\"key\":\"intertops\",\"lastUpdate\":\"2022-11-01T11:55:53Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Sporting Lisbon\",\"price\":1.33},{\"name\":\"Vitória SC\",\"price\":8},{\"name\":\"Draw\",\"price\":4.75}]}]},{\"key\":\"unibet_eu\",\"lastUpdate\":\"2022-11-01T11:59:54Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Sporting Lisbon\",\"price\":1.35},{\"name\":\"Vitória SC\",\"price\":7.5},{\"name\":\"Draw\",\"price\":5}]}]},{\"key\":\"sport888\",\"lastUpdate\":\"2022-11-01T11:54:10Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Sporting Lisbon\",\"price\":1.32},{\"name\":\"Vitória SC\",\"price\":7.5},{\"name\":\"Draw\",\"price\":4.9}]}]},{\"key\":\"betfair\",\"lastUpdate\":\"2022-11-01T11:56:34Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Sporting Lisbon\",\"price\":1.33},{\"name\":\"Vitória SC\",\"price\":1.05},{\"name\":\"Draw\",\"price\":4.3}]},{\"key\":\"h2h_lay\",\"outcomes\":[{\"name\":\"Sporting Lisbon\",\"price\":1.43},{\"name\":\"Vitória SC\",\"price\":12},{\"name\":\"Draw\",\"price\":6.2}]}]},{\"key\":\"pinnacle\",\"lastUpdate\":\"2022-11-01T11:58:49Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Sporting Lisbon\",\"price\":1.37},{\"name\":\"Vitória SC\",\"price\":9},{\"name\":\"Draw\",\"price\":5.06}]}]},{\"key\":\"betonlineag\",\"lastUpdate\":\"2022-11-01T11:54:11Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Sporting Lisbon\",\"price\":1.34},{\"name\":\"Vitória SC\",\"price\":8},{\"name\":\"Draw\",\"price\":4.9}]}]}],\"commenceTime\":\"2022-11-05T20:30:00.000Z\",\"completed\":false,\"homeTeam\":\"Sporting Lisbon\",\"scores\":null},{\"id\":\"72cdea24cebb5465e577b8fe3f3cdfeb\",\"awayTeam\":\"Boavista Porto\",\"bookmakers\":[{\"key\":\"mybookieag\",\"lastUpdate\":\"2022-11-01T11:55:46Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Boavista Porto\",\"price\":3.1},{\"name\":\"Rio Ave FC\",\"price\":2.35},{\"name\":\"Draw\",\"price\":3.1}]}]},{\"key\":\"betsson\",\"lastUpdate\":\"2022-11-01T11:59:32Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Boavista Porto\",\"price\":3.1},{\"name\":\"Rio Ave FC\",\"price\":2.35},{\"name\":\"Draw\",\"price\":3.2}]}]},{\"key\":\"marathonbet\",\"lastUpdate\":\"2022-11-01T11:54:09Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Boavista Porto\",\"price\":3.18},{\"name\":\"Rio Ave FC\",\"price\":2.32},{\"name\":\"Draw\",\"price\":3.18}]}]},{\"key\":\"williamhill\",\"lastUpdate\":\"2022-11-01T11:54:10Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Boavista Porto\",\"price\":3.1},{\"name\":\"Rio Ave FC\",\"price\":2.38},{\"name\":\"Draw\",\"price\":3.1}]}]},{\"key\":\"onexbet\",\"lastUpdate\":\"2022-11-01T11:53:53Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Boavista Porto\",\"price\":3.18},{\"name\":\"Rio Ave FC\",\"price\":2.34},{\"name\":\"Draw\",\"price\":3.18}]}]},{\"key\":\"betclic\",\"lastUpdate\":\"2022-11-01T11:58:49Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Boavista Porto\",\"price\":3.17},{\"name\":\"Rio Ave FC\",\"price\":2.39},{\"name\":\"Draw\",\"price\":3.15}]}]},{\"key\":\"pinnacle\",\"lastUpdate\":\"2022-11-01T11:58:49Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Boavista Porto\",\"price\":3.28},{\"name\":\"Rio Ave FC\",\"price\":2.45},{\"name\":\"Draw\",\"price\":3.12}]}]},{\"key\":\"intertops\",\"lastUpdate\":\"2022-11-01T11:55:53Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Boavista Porto\",\"price\":3.1},{\"name\":\"Rio Ave FC\",\"price\":2.35},{\"name\":\"Draw\",\"price\":2.95}]}]},{\"key\":\"unibet_eu\",\"lastUpdate\":\"2022-11-01T11:59:54Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Boavista Porto\",\"price\":3.2},{\"name\":\"Rio Ave FC\",\"price\":2.32},{\"name\":\"Draw\",\"price\":3.1}]}]},{\"key\":\"sport888\",\"lastUpdate\":\"2022-11-01T11:54:10Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Boavista Porto\",\"price\":3.1},{\"name\":\"Rio Ave FC\",\"price\":2.25},{\"name\":\"Draw\",\"price\":3}]}]},{\"key\":\"betfair\",\"lastUpdate\":\"2022-11-01T11:56:34Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Boavista Porto\",\"price\":2.8},{\"name\":\"Rio Ave FC\",\"price\":2.2},{\"name\":\"Draw\",\"price\":2.68}]},{\"key\":\"h2h_lay\",\"outcomes\":[{\"name\":\"Boavista Porto\",\"price\":4.1},{\"name\":\"Rio Ave FC\",\"price\":2.76},{\"name\":\"Draw\",\"price\":4.1}]}]}],\"commenceTime\":\"2022-11-06T15:30:00.000Z\",\"completed\":false,\"homeTeam\":\"Rio Ave FC\",\"scores\":null},{\"id\":\"c7ede9e77ceb9af1e3d037b8dcd44116\",\"awayTeam\":\"Casa Pia\",\"bookmakers\":[{\"key\":\"mybookieag\",\"lastUpdate\":\"2022-11-01T11:55:46Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Braga\",\"price\":1.4},{\"name\":\"Casa Pia\",\"price\":7},{\"name\":\"Draw\",\"price\":4.7}]}]},{\"key\":\"betsson\",\"lastUpdate\":\"2022-11-01T11:59:32Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Braga\",\"price\":1.4},{\"name\":\"Casa Pia\",\"price\":7.5},{\"name\":\"Draw\",\"price\":4.75}]}]},{\"key\":\"marathonbet\",\"lastUpdate\":\"2022-11-01T11:54:09Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Braga\",\"price\":1.39},{\"name\":\"Casa Pia\",\"price\":7.6},{\"name\":\"Draw\",\"price\":4.75}]}]},{\"key\":\"williamhill\",\"lastUpdate\":\"2022-11-01T11:54:10Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Braga\",\"price\":1.4},{\"name\":\"Casa Pia\",\"price\":8},{\"name\":\"Draw\",\"price\":4.4}]}]},{\"key\":\"onexbet\",\"lastUpdate\":\"2022-11-01T11:53:53Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Braga\",\"price\":1.4},{\"name\":\"Casa Pia\",\"price\":7.5},{\"name\":\"Draw\",\"price\":4.74}]}]},{\"key\":\"betclic\",\"lastUpdate\":\"2022-11-01T11:58:49Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Braga\",\"price\":1.42},{\"name\":\"Casa Pia\",\"price\":7.3},{\"name\":\"Draw\",\"price\":4.8}]}]},{\"key\":\"pinnacle\",\"lastUpdate\":\"2022-11-01T11:58:49Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Braga\",\"price\":1.39},{\"name\":\"Casa Pia\",\"price\":8.41},{\"name\":\"Draw\",\"price\":4.98}]}]},{\"key\":\"intertops\",\"lastUpdate\":\"2022-11-01T11:55:53Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Braga\",\"price\":1.36},{\"name\":\"Casa Pia\",\"price\":7.5},{\"name\":\"Draw\",\"price\":4.6}]}]},{\"key\":\"unibet_eu\",\"lastUpdate\":\"2022-11-01T11:59:54Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Braga\",\"price\":1.38},{\"name\":\"Casa Pia\",\"price\":7.5},{\"name\":\"Draw\",\"price\":4.7}]}]},{\"key\":\"sport888\",\"lastUpdate\":\"2022-11-01T11:54:10Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Braga\",\"price\":1.34},{\"name\":\"Casa Pia\",\"price\":7.5},{\"name\":\"Draw\",\"price\":4.6}]}]},{\"key\":\"betfair\",\"lastUpdate\":\"2022-11-01T11:56:34Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Braga\",\"price\":1.38},{\"name\":\"Casa Pia\",\"price\":1.04},{\"name\":\"Draw\",\"price\":1.1}]},{\"key\":\"h2h_lay\",\"outcomes\":[{\"name\":\"Braga\",\"price\":1.54},{\"name\":\"Casa Pia\",\"price\":21},{\"name\":\"Draw\",\"price\":950}]}]}],\"commenceTime\":\"2022-11-06T18:00:00.000Z\",\"completed\":false,\"homeTeam\":\"Braga\",\"scores\":null},{\"id\":\"7ad4bb5d9ab1e9c67014a87403edf80a\",\"awayTeam\":\"Famalicão\",\"bookmakers\":[{\"key\":\"mybookieag\",\"lastUpdate\":\"2022-11-01T11:55:46Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"CS Maritimo\",\"price\":2.8},{\"name\":\"Famalicão\",\"price\":2.55},{\"name\":\"Draw\",\"price\":3.15}]}]},{\"key\":\"betsson\",\"lastUpdate\":\"2022-11-01T11:59:32Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"CS Maritimo\",\"price\":2.8},{\"name\":\"Famalicão\",\"price\":2.55},{\"name\":\"Draw\",\"price\":3.2}]}]},{\"key\":\"marathonbet\",\"lastUpdate\":\"2022-11-01T11:54:09Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"CS Maritimo\",\"price\":2.75},{\"name\":\"Famalicão\",\"price\":2.56},{\"name\":\"Draw\",\"price\":3.26}]}]},{\"key\":\"williamhill\",\"lastUpdate\":\"2022-11-01T11:54:10Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"CS Maritimo\",\"price\":2.75},{\"name\":\"Famalicão\",\"price\":2.6},{\"name\":\"Draw\",\"price\":3.1}]}]},{\"key\":\"onexbet\",\"lastUpdate\":\"2022-11-01T11:53:53Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"CS Maritimo\",\"price\":2.75},{\"name\":\"Famalicão\",\"price\":2.56},{\"name\":\"Draw\",\"price\":3.26}]}]},{\"key\":\"betclic\",\"lastUpdate\":\"2022-11-01T11:58:49Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"CS Maritimo\",\"price\":2.84},{\"name\":\"Famalicão\",\"price\":2.58},{\"name\":\"Draw\",\"price\":3.22}]}]},{\"key\":\"intertops\",\"lastUpdate\":\"2022-11-01T11:55:53Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"CS Maritimo\",\"price\":2.7},{\"name\":\"Famalicão\",\"price\":2.5},{\"name\":\"Draw\",\"price\":3.2}]}]},{\"key\":\"sport888\",\"lastUpdate\":\"2022-11-01T11:54:10Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"CS Maritimo\",\"price\":2.65},{\"name\":\"Famalicão\",\"price\":2.5},{\"name\":\"Draw\",\"price\":3.05}]}]},{\"key\":\"unibet_eu\",\"lastUpdate\":\"2022-11-01T11:59:54Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"CS Maritimo\",\"price\":2.75},{\"name\":\"Famalicão\",\"price\":2.6},{\"name\":\"Draw\",\"price\":3.15}]}]},{\"key\":\"betfair\",\"lastUpdate\":\"2022-11-01T11:56:34Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"CS Maritimo\",\"price\":1.04},{\"name\":\"Famalicão\",\"price\":1.37},{\"name\":\"Draw\",\"price\":1.01}]},{\"key\":\"h2h_lay\",\"outcomes\":[{\"name\":\"CS Maritimo\",\"price\":3.7},{\"name\":\"Famalicão\",\"price\":3.25},{\"name\":\"Draw\",\"price\":950}]}]}],\"commenceTime\":\"2022-11-06T18:00:00.000Z\",\"completed\":false,\"homeTeam\":\"CS Maritimo\",\"scores\":null},{\"id\":\"0db34ba8228c8124f7d026b0ce1724d2\",\"awayTeam\":\"Benfica\",\"bookmakers\":[{\"key\":\"onexbet\",\"lastUpdate\":\"2022-11-01T11:53:53Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Benfica\",\"price\":1.31},{\"name\":\"Estoril\",\"price\":9.1},{\"name\":\"Draw\",\"price\":5.4}]}]},{\"key\":\"williamhill\",\"lastUpdate\":\"2022-11-01T11:54:10Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Benfica\",\"price\":1.29},{\"name\":\"Estoril\",\"price\":11},{\"name\":\"Draw\",\"price\":5}]}]},{\"key\":\"mybookieag\",\"lastUpdate\":\"2022-11-01T11:55:46Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Benfica\",\"price\":1.29},{\"name\":\"Estoril\",\"price\":9},{\"name\":\"Draw\",\"price\":5.4}]}]},{\"key\":\"marathonbet\",\"lastUpdate\":\"2022-11-01T11:54:09Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Benfica\",\"price\":1.3},{\"name\":\"Estoril\",\"price\":9.4},{\"name\":\"Draw\",\"price\":5.4}]}]},{\"key\":\"betclic\",\"lastUpdate\":\"2022-11-01T11:58:49Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Benfica\",\"price\":1.31},{\"name\":\"Estoril\",\"price\":9.35},{\"name\":\"Draw\",\"price\":5.6}]}]},{\"key\":\"betsson\",\"lastUpdate\":\"2022-11-01T11:59:32Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Benfica\",\"price\":1.28},{\"name\":\"Estoril\",\"price\":10},{\"name\":\"Draw\",\"price\":5.5}]}]},{\"key\":\"pinnacle\",\"lastUpdate\":\"2022-11-01T11:58:49Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Benfica\",\"price\":1.32},{\"name\":\"Estoril\",\"price\":9.62},{\"name\":\"Draw\",\"price\":5.63}]}]},{\"key\":\"intertops\",\"lastUpdate\":\"2022-11-01T11:55:53Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Benfica\",\"price\":1.28},{\"name\":\"Estoril\",\"price\":8.5},{\"name\":\"Draw\",\"price\":5}]}]},{\"key\":\"unibet_eu\",\"lastUpdate\":\"2022-11-01T11:59:54Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Benfica\",\"price\":1.3},{\"name\":\"Estoril\",\"price\":8.5},{\"name\":\"Draw\",\"price\":5.25}]}]},{\"key\":\"sport888\",\"lastUpdate\":\"2022-11-01T11:54:10Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Benfica\",\"price\":1.27},{\"name\":\"Estoril\",\"price\":8.5},{\"name\":\"Draw\",\"price\":5.1}]}]},{\"key\":\"betfair\",\"lastUpdate\":\"2022-11-01T11:56:34Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Benfica\",\"price\":1.28},{\"name\":\"Estoril\",\"price\":1.04},{\"name\":\"Draw\",\"price\":4.6}]},{\"key\":\"h2h_lay\",\"outcomes\":[{\"name\":\"Benfica\",\"price\":1.39},{\"name\":\"Estoril\",\"price\":60},{\"name\":\"Draw\",\"price\":950}]}]}],\"commenceTime\":\"2022-11-06T20:30:00.000Z\",\"completed\":false,\"homeTeam\":\"Estoril\",\"scores\":null},{\"id\":\"5a673392e3835c2b073ccd468d652593\",\"awayTeam\":\"Santa Clara\",\"bookmakers\":[{\"key\":\"betclic\",\"lastUpdate\":\"2022-11-01T11:58:49Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Chaves\",\"price\":2.32},{\"name\":\"Santa Clara\",\"price\":3.17},{\"name\":\"Draw\",\"price\":3.28}]}]},{\"key\":\"williamhill\",\"lastUpdate\":\"2022-11-01T11:54:10Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Chaves\",\"price\":2.3},{\"name\":\"Santa Clara\",\"price\":3.2},{\"name\":\"Draw\",\"price\":3.1}]}]},{\"key\":\"onexbet\",\"lastUpdate\":\"2022-11-01T11:53:53Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Chaves\",\"price\":2.26},{\"name\":\"Santa Clara\",\"price\":3.19},{\"name\":\"Draw\",\"price\":3.32}]}]},{\"key\":\"marathonbet\",\"lastUpdate\":\"2022-11-01T11:54:09Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Chaves\",\"price\":2.24},{\"name\":\"Santa Clara\",\"price\":3.2},{\"name\":\"Draw\",\"price\":3.32}]}]},{\"key\":\"mybookieag\",\"lastUpdate\":\"2022-11-01T11:55:46Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Chaves\",\"price\":2.29},{\"name\":\"Santa Clara\",\"price\":3.1},{\"name\":\"Draw\",\"price\":3.2}]}]},{\"key\":\"pinnacle\",\"lastUpdate\":\"2022-11-01T11:58:49Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Chaves\",\"price\":2.37},{\"name\":\"Santa Clara\",\"price\":3.38},{\"name\":\"Draw\",\"price\":3.17}]}]},{\"key\":\"unibet_eu\",\"lastUpdate\":\"2022-11-01T11:59:54Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Chaves\",\"price\":2.28},{\"name\":\"Santa Clara\",\"price\":3.25},{\"name\":\"Draw\",\"price\":3.15}]}]},{\"key\":\"sport888\",\"lastUpdate\":\"2022-11-01T11:54:10Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Chaves\",\"price\":2.2},{\"name\":\"Santa Clara\",\"price\":3.15},{\"name\":\"Draw\",\"price\":3.05}]}]},{\"key\":\"betsson\",\"lastUpdate\":\"2022-11-01T11:59:32Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Chaves\",\"price\":2.35},{\"name\":\"Santa Clara\",\"price\":3},{\"name\":\"Draw\",\"price\":3.3}]}]},{\"key\":\"intertops\",\"lastUpdate\":\"2022-11-01T11:55:53Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Chaves\",\"price\":2.25},{\"name\":\"Santa Clara\",\"price\":3.2},{\"name\":\"Draw\",\"price\":3.05}]}]}],\"commenceTime\":\"2022-11-07T20:15:00.000Z\",\"completed\":false,\"homeTeam\":\"Chaves\",\"scores\":null}]\n");
        //Game game1 = new Game("1","hometeam","awayteam","commenceTime",true,"2x0","fut");
        for (Game game : g) {
            game.generateResult();
            GameDAO.store(game);
        }

        for (int i = 1; i <= 20; i++){
            model.register("user" + i,"user"+i + "@gmail.com","12345678", String.valueOf(12345678+i),0);
            ((Better)UserDAO.get("user"+i+"@gmail.com")).getWallet().setEuros(new Random().nextFloat()*100);
            ((Better)UserDAO.get("user"+i+"@gmail.com")).getWallet().setDollars(new Random().nextFloat()*100);
        }
        model.register("especialista","esp@gmail.com","12345678","00000000",1);
        model.register("admin","admin@gmail.com","12345678","000000000",1);
        try {
            new RasBetUI(model).run();
        }
        catch (Exception e) {
            System.out.println("Erro fatal: " + e.getMessage() + " [" + e.toString() + "]");
            e.printStackTrace();
        }
    }
}
