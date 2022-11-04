import DataLayer.*;
import Model.*;
import UI.RasBetUI;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

public class Main {
    
    public static void main(String[] args) {
        RasBetFacade model = new RasBetFacade();

        loadData(model);
        System.out.println(LocalDateTime.now().toString());
        try {
            new RasBetUI(model).run();
        }
        catch (Exception e) {
            System.out.println("Erro fatal: " + e.getMessage() + " [" + e.toString() + "]");
            e.printStackTrace();
        }
    }

    public static void loadData(RasBetFacade model){

        //fetchDatafromAPI();

        List<Game> g = model.parseJson("[{\"id\":\"d412d9b63fc1ee28b5c7ed331d0127ce\",\"awayTeam\":\"Famalicão\",\"bookmakers\":[{\"key\":\"mybookieag\",\"lastUpdate\":\"2022-10-31T21:44:30Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Famalicão\",\"price\":12.5},{\"name\":\"Vitória SC\",\"price\":1.32},{\"name\":\"Draw\",\"price\":3.8}]}]},{\"key\":\"pinnacle\",\"lastUpdate\":\"2022-10-31T21:44:29Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Famalicão\",\"price\":83.92},{\"name\":\"Vitória SC\",\"price\":1.04},{\"name\":\"Draw\",\"price\":18.66}]}]},{\"key\":\"betclic\",\"lastUpdate\":\"2022-10-31T21:40:01Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Famalicão\",\"price\":80},{\"name\":\"Vitória SC\",\"price\":1},{\"name\":\"Draw\",\"price\":17}]}]},{\"key\":\"betsson\",\"lastUpdate\":\"2022-10-31T21:44:32Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Famalicão\",\"price\":46},{\"name\":\"Vitória SC\",\"price\":1},{\"name\":\"Draw\",\"price\":23}]}]},{\"key\":\"williamhill\",\"lastUpdate\":\"2022-10-31T21:43:37Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Famalicão\",\"price\":3.3},{\"name\":\"Vitória SC\",\"price\":2.25},{\"name\":\"Draw\",\"price\":3.1}]}]},{\"key\":\"betfair\",\"lastUpdate\":\"2022-10-31T21:40:02Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Famalicão\",\"price\":120},{\"name\":\"Vitória SC\",\"price\":1.06},{\"name\":\"Draw\",\"price\":18.5}]},{\"key\":\"h2h_lay\",\"outcomes\":[{\"name\":\"Famalicão\",\"price\":180},{\"name\":\"Vitória SC\",\"price\":1.07},{\"name\":\"Draw\",\"price\":21}]}]}],\"commenceTime\":\"2022-10-31T20:16:08.000Z\",\"completed\":true,\"homeTeam\":\"Vitória SC\",\"scores\":\"3x2\"},{\"id\":\"538136a794711c8c9bc24b48353c396c\",\"awayTeam\":\"Portimonense\",\"bookmakers\":[{\"key\":\"marathonbet\",\"lastUpdate\":\"2022-11-02T11:55:14Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Gil Vicente\",\"price\":2.16},{\"name\":\"Portimonense\",\"price\":3.3},{\"name\":\"Draw\",\"price\":3.4}]}]},{\"key\":\"betclic\",\"lastUpdate\":\"2022-11-02T11:56:47Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Gil Vicente\",\"price\":2.18},{\"name\":\"Portimonense\",\"price\":3.32},{\"name\":\"Draw\",\"price\":3.45}]}]},{\"key\":\"betsson\",\"lastUpdate\":\"2022-11-02T11:55:42Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Gil Vicente\",\"price\":2.15},{\"name\":\"Portimonense\",\"price\":3.3},{\"name\":\"Draw\",\"price\":3.4}]}]},{\"key\":\"onexbet\",\"lastUpdate\":\"2022-11-02T11:50:17Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Gil Vicente\",\"price\":2.18},{\"name\":\"Portimonense\",\"price\":3.28},{\"name\":\"Draw\",\"price\":3.4}]}]},{\"key\":\"mybookieag\",\"lastUpdate\":\"2022-11-02T11:54:08Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Gil Vicente\",\"price\":2.17},{\"name\":\"Portimonense\",\"price\":3.3},{\"name\":\"Draw\",\"price\":3.4}]}]},{\"key\":\"williamhill\",\"lastUpdate\":\"2022-11-02T11:55:23Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Gil Vicente\",\"price\":2.2},{\"name\":\"Portimonense\",\"price\":3.3},{\"name\":\"Draw\",\"price\":3.25}]}]},{\"key\":\"betfair\",\"lastUpdate\":\"2022-11-02T11:58:10Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Gil Vicente\",\"price\":2.22},{\"name\":\"Portimonense\",\"price\":3.4},{\"name\":\"Draw\",\"price\":3.35}]},{\"key\":\"h2h_lay\",\"outcomes\":[{\"name\":\"Gil Vicente\",\"price\":2.4},{\"name\":\"Portimonense\",\"price\":3.75},{\"name\":\"Draw\",\"price\":3.75}]}]},{\"key\":\"pinnacle\",\"lastUpdate\":\"2022-11-02T11:56:47Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Gil Vicente\",\"price\":2.24},{\"name\":\"Portimonense\",\"price\":3.38},{\"name\":\"Draw\",\"price\":3.51}]}]},{\"key\":\"betonlineag\",\"lastUpdate\":\"2022-11-02T11:55:29Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Gil Vicente\",\"price\":2.19},{\"name\":\"Portimonense\",\"price\":3.05},{\"name\":\"Draw\",\"price\":3.4}]}]},{\"key\":\"unibet_eu\",\"lastUpdate\":\"2022-11-02T11:55:20Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Gil Vicente\",\"price\":2.02},{\"name\":\"Portimonense\",\"price\":3.5},{\"name\":\"Draw\",\"price\":3.4}]}]},{\"key\":\"sport888\",\"lastUpdate\":\"2022-11-02T11:55:23Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Gil Vicente\",\"price\":1.98},{\"name\":\"Portimonense\",\"price\":3.4},{\"name\":\"Draw\",\"price\":3.35}]}]},{\"key\":\"intertops\",\"lastUpdate\":\"2022-11-02T11:56:28Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Gil Vicente\",\"price\":2.1},{\"name\":\"Portimonense\",\"price\":3.2},{\"name\":\"Draw\",\"price\":3.3}]}]},{\"key\":\"matchbook\",\"lastUpdate\":\"2022-11-02T11:55:14Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Gil Vicente\",\"price\":2.22},{\"name\":\"Portimonense\",\"price\":3.35},{\"name\":\"Draw\",\"price\":3.45}]},{\"key\":\"h2h_lay\",\"outcomes\":[{\"name\":\"Gil Vicente\",\"price\":2.36},{\"name\":\"Portimonense\",\"price\":3.65},{\"name\":\"Draw\",\"price\":3.75}]}]}],\"commenceTime\":\"2022-11-04T20:15:00.000Z\",\"completed\":false,\"homeTeam\":\"Gil Vicente\",\"scores\":null},{\"id\":\"be09fd32746c6c2f11a64157039dc992\",\"awayTeam\":\"Arouca\",\"bookmakers\":[{\"key\":\"betsson\",\"lastUpdate\":\"2022-11-02T11:55:42Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Arouca\",\"price\":4.2},{\"name\":\"Vizela\",\"price\":1.9},{\"name\":\"Draw\",\"price\":3.4}]}]},{\"key\":\"marathonbet\",\"lastUpdate\":\"2022-11-02T11:55:14Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Arouca\",\"price\":4},{\"name\":\"Vizela\",\"price\":1.92},{\"name\":\"Draw\",\"price\":3.44}]}]},{\"key\":\"onexbet\",\"lastUpdate\":\"2022-11-02T11:50:17Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Arouca\",\"price\":3.96},{\"name\":\"Vizela\",\"price\":1.96},{\"name\":\"Draw\",\"price\":3.38}]}]},{\"key\":\"betclic\",\"lastUpdate\":\"2022-11-02T11:56:47Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Arouca\",\"price\":4.23},{\"name\":\"Vizela\",\"price\":1.94},{\"name\":\"Draw\",\"price\":3.35}]}]},{\"key\":\"mybookieag\",\"lastUpdate\":\"2022-11-02T11:54:08Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Arouca\",\"price\":4.1},{\"name\":\"Vizela\",\"price\":1.92},{\"name\":\"Draw\",\"price\":3.3}]}]},{\"key\":\"williamhill\",\"lastUpdate\":\"2022-11-02T11:55:23Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Arouca\",\"price\":4.2},{\"name\":\"Vizela\",\"price\":1.91},{\"name\":\"Draw\",\"price\":3.25}]}]},{\"key\":\"betfair\",\"lastUpdate\":\"2022-11-02T11:58:10Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Arouca\",\"price\":4.3},{\"name\":\"Vizela\",\"price\":1.99},{\"name\":\"Draw\",\"price\":3.3}]},{\"key\":\"h2h_lay\",\"outcomes\":[{\"name\":\"Arouca\",\"price\":5.6},{\"name\":\"Vizela\",\"price\":2.12},{\"name\":\"Draw\",\"price\":3.65}]}]},{\"key\":\"pinnacle\",\"lastUpdate\":\"2022-11-02T11:56:47Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Arouca\",\"price\":4.5},{\"name\":\"Vizela\",\"price\":1.95},{\"name\":\"Draw\",\"price\":3.34}]}]},{\"key\":\"intertops\",\"lastUpdate\":\"2022-11-02T11:56:28Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Arouca\",\"price\":4.2},{\"name\":\"Vizela\",\"price\":1.9},{\"name\":\"Draw\",\"price\":3.15}]}]},{\"key\":\"unibet_eu\",\"lastUpdate\":\"2022-11-02T11:55:20Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Arouca\",\"price\":4.2},{\"name\":\"Vizela\",\"price\":1.89},{\"name\":\"Draw\",\"price\":3.3}]}]},{\"key\":\"sport888\",\"lastUpdate\":\"2022-11-02T11:55:23Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Arouca\",\"price\":4.1},{\"name\":\"Vizela\",\"price\":1.86},{\"name\":\"Draw\",\"price\":3.2}]}]},{\"key\":\"betonlineag\",\"lastUpdate\":\"2022-11-02T11:55:29Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Arouca\",\"price\":4},{\"name\":\"Vizela\",\"price\":1.91},{\"name\":\"Draw\",\"price\":3.25}]}]}],\"commenceTime\":\"2022-11-05T15:30:00.000Z\",\"completed\":false,\"homeTeam\":\"Vizela\",\"scores\":null},{\"id\":\"c8d53fadc30913fa823bea3999311b81\",\"awayTeam\":\"Pacos de Ferreira\",\"bookmakers\":[{\"key\":\"betsson\",\"lastUpdate\":\"2022-11-02T11:55:42Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"FC Porto\",\"price\":1.09},{\"name\":\"Pacos de Ferreira\",\"price\":29},{\"name\":\"Draw\",\"price\":9}]}]},{\"key\":\"marathonbet\",\"lastUpdate\":\"2022-11-02T11:55:14Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"FC Porto\",\"price\":1.1},{\"name\":\"Pacos de Ferreira\",\"price\":22},{\"name\":\"Draw\",\"price\":9.1}]}]},{\"key\":\"onexbet\",\"lastUpdate\":\"2022-11-02T11:50:17Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"FC Porto\",\"price\":1.1},{\"name\":\"Pacos de Ferreira\",\"price\":24},{\"name\":\"Draw\",\"price\":9.1}]}]},{\"key\":\"betclic\",\"lastUpdate\":\"2022-11-02T11:56:47Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"FC Porto\",\"price\":1.11},{\"name\":\"Pacos de Ferreira\",\"price\":25},{\"name\":\"Draw\",\"price\":9.3}]}]},{\"key\":\"mybookieag\",\"lastUpdate\":\"2022-11-02T11:54:08Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"FC Porto\",\"price\":1.1},{\"name\":\"Pacos de Ferreira\",\"price\":23},{\"name\":\"Draw\",\"price\":8.6}]}]},{\"key\":\"williamhill\",\"lastUpdate\":\"2022-11-02T11:55:23Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"FC Porto\",\"price\":1.1},{\"name\":\"Pacos de Ferreira\",\"price\":29},{\"name\":\"Draw\",\"price\":8}]}]},{\"key\":\"pinnacle\",\"lastUpdate\":\"2022-11-02T11:56:47Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"FC Porto\",\"price\":1.13},{\"name\":\"Pacos de Ferreira\",\"price\":22.34},{\"name\":\"Draw\",\"price\":9.32}]}]},{\"key\":\"betfair\",\"lastUpdate\":\"2022-11-02T11:58:10Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"FC Porto\",\"price\":1.12},{\"name\":\"Pacos de Ferreira\",\"price\":1.08},{\"name\":\"Draw\",\"price\":8.8}]},{\"key\":\"h2h_lay\",\"outcomes\":[{\"name\":\"FC Porto\",\"price\":1.16},{\"name\":\"Pacos de Ferreira\",\"price\":42},{\"name\":\"Draw\",\"price\":14}]}]},{\"key\":\"intertops\",\"lastUpdate\":\"2022-11-02T11:56:28Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"FC Porto\",\"price\":1.1},{\"name\":\"Pacos de Ferreira\",\"price\":21},{\"name\":\"Draw\",\"price\":8}]}]},{\"key\":\"unibet_eu\",\"lastUpdate\":\"2022-11-02T11:55:20Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"FC Porto\",\"price\":1.1},{\"name\":\"Pacos de Ferreira\",\"price\":19},{\"name\":\"Draw\",\"price\":9}]}]},{\"key\":\"sport888\",\"lastUpdate\":\"2022-11-02T11:55:23Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"FC Porto\",\"price\":1.08},{\"name\":\"Pacos de Ferreira\",\"price\":18},{\"name\":\"Draw\",\"price\":8.5}]}]},{\"key\":\"betonlineag\",\"lastUpdate\":\"2022-11-02T11:55:29Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"FC Porto\",\"price\":1.09},{\"name\":\"Pacos de Ferreira\",\"price\":20},{\"name\":\"Draw\",\"price\":8.8}]}]}],\"commenceTime\":\"2022-11-05T18:00:00.000Z\",\"completed\":false,\"homeTeam\":\"FC Porto\",\"scores\":null},{\"id\":\"6ae5f22198593f180590bf8161bbf910\",\"awayTeam\":\"Vitória SC\",\"bookmakers\":[{\"key\":\"mybookieag\",\"lastUpdate\":\"2022-11-02T11:54:08Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Sporting Lisbon\",\"price\":1.32},{\"name\":\"Vitória SC\",\"price\":8.6},{\"name\":\"Draw\",\"price\":5}]}]},{\"key\":\"betsson\",\"lastUpdate\":\"2022-11-02T11:55:42Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Sporting Lisbon\",\"price\":1.33},{\"name\":\"Vitória SC\",\"price\":9.5},{\"name\":\"Draw\",\"price\":5}]}]},{\"key\":\"betclic\",\"lastUpdate\":\"2022-11-02T11:56:47Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Sporting Lisbon\",\"price\":1.34},{\"name\":\"Vitória SC\",\"price\":9.05},{\"name\":\"Draw\",\"price\":5.2}]}]},{\"key\":\"marathonbet\",\"lastUpdate\":\"2022-11-02T11:55:14Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Sporting Lisbon\",\"price\":1.31},{\"name\":\"Vitória SC\",\"price\":9.5},{\"name\":\"Draw\",\"price\":5.2}]}]},{\"key\":\"williamhill\",\"lastUpdate\":\"2022-11-02T11:55:23Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Sporting Lisbon\",\"price\":1.33},{\"name\":\"Vitória SC\",\"price\":10},{\"name\":\"Draw\",\"price\":4.75}]}]},{\"key\":\"intertops\",\"lastUpdate\":\"2022-11-02T11:56:28Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Sporting Lisbon\",\"price\":1.28},{\"name\":\"Vitória SC\",\"price\":9},{\"name\":\"Draw\",\"price\":5}]}]},{\"key\":\"unibet_eu\",\"lastUpdate\":\"2022-11-02T11:55:20Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Sporting Lisbon\",\"price\":1.35},{\"name\":\"Vitória SC\",\"price\":7.5},{\"name\":\"Draw\",\"price\":5}]}]},{\"key\":\"sport888\",\"lastUpdate\":\"2022-11-02T11:55:23Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Sporting Lisbon\",\"price\":1.32},{\"name\":\"Vitória SC\",\"price\":7.5},{\"name\":\"Draw\",\"price\":4.9}]}]},{\"key\":\"betfair\",\"lastUpdate\":\"2022-11-02T11:58:10Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Sporting Lisbon\",\"price\":1.3},{\"name\":\"Vitória SC\",\"price\":7.2},{\"name\":\"Draw\",\"price\":4.8}]},{\"key\":\"h2h_lay\",\"outcomes\":[{\"name\":\"Sporting Lisbon\",\"price\":1.39},{\"name\":\"Vitória SC\",\"price\":14},{\"name\":\"Draw\",\"price\":6.6}]}]},{\"key\":\"pinnacle\",\"lastUpdate\":\"2022-11-02T11:56:47Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Sporting Lisbon\",\"price\":1.32},{\"name\":\"Vitória SC\",\"price\":10.37},{\"name\":\"Draw\",\"price\":5.45}]}]},{\"key\":\"betonlineag\",\"lastUpdate\":\"2022-11-02T11:55:29Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Sporting Lisbon\",\"price\":1.32},{\"name\":\"Vitória SC\",\"price\":8.3},{\"name\":\"Draw\",\"price\":5}]}]}],\"commenceTime\":\"2022-11-05T20:30:00.000Z\",\"completed\":false,\"homeTeam\":\"Sporting Lisbon\",\"scores\":null},{\"id\":\"72cdea24cebb5465e577b8fe3f3cdfeb\",\"awayTeam\":\"Boavista Porto\",\"bookmakers\":[{\"key\":\"mybookieag\",\"lastUpdate\":\"2022-11-02T11:54:08Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Boavista Porto\",\"price\":3.1},{\"name\":\"Rio Ave FC\",\"price\":2.35},{\"name\":\"Draw\",\"price\":3.1}]}]},{\"key\":\"betsson\",\"lastUpdate\":\"2022-11-02T11:55:42Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Boavista Porto\",\"price\":3.1},{\"name\":\"Rio Ave FC\",\"price\":2.4},{\"name\":\"Draw\",\"price\":3.1}]}]},{\"key\":\"marathonbet\",\"lastUpdate\":\"2022-11-02T11:55:14Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Boavista Porto\",\"price\":3.05},{\"name\":\"Rio Ave FC\",\"price\":2.36},{\"name\":\"Draw\",\"price\":3.24}]}]},{\"key\":\"williamhill\",\"lastUpdate\":\"2022-11-02T11:55:23Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Boavista Porto\",\"price\":3.1},{\"name\":\"Rio Ave FC\",\"price\":2.38},{\"name\":\"Draw\",\"price\":3.1}]}]},{\"key\":\"onexbet\",\"lastUpdate\":\"2022-11-02T11:50:17Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Boavista Porto\",\"price\":3.06},{\"name\":\"Rio Ave FC\",\"price\":2.4},{\"name\":\"Draw\",\"price\":3.2}]}]},{\"key\":\"betclic\",\"lastUpdate\":\"2022-11-02T11:56:47Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Boavista Porto\",\"price\":3.17},{\"name\":\"Rio Ave FC\",\"price\":2.39},{\"name\":\"Draw\",\"price\":3.15}]}]},{\"key\":\"pinnacle\",\"lastUpdate\":\"2022-11-02T11:56:47Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Boavista Porto\",\"price\":3.15},{\"name\":\"Rio Ave FC\",\"price\":2.47},{\"name\":\"Draw\",\"price\":3.22}]}]},{\"key\":\"intertops\",\"lastUpdate\":\"2022-11-02T11:56:28Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Boavista Porto\",\"price\":3},{\"name\":\"Rio Ave FC\",\"price\":2.4},{\"name\":\"Draw\",\"price\":3}]}]},{\"key\":\"unibet_eu\",\"lastUpdate\":\"2022-11-02T11:55:20Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Boavista Porto\",\"price\":3.2},{\"name\":\"Rio Ave FC\",\"price\":2.32},{\"name\":\"Draw\",\"price\":3.1}]}]},{\"key\":\"sport888\",\"lastUpdate\":\"2022-11-02T11:55:23Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Boavista Porto\",\"price\":3.1},{\"name\":\"Rio Ave FC\",\"price\":2.25},{\"name\":\"Draw\",\"price\":3}]}]},{\"key\":\"betfair\",\"lastUpdate\":\"2022-11-02T11:58:10Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Boavista Porto\",\"price\":2.74},{\"name\":\"Rio Ave FC\",\"price\":2.32},{\"name\":\"Draw\",\"price\":2.72}]},{\"key\":\"h2h_lay\",\"outcomes\":[{\"name\":\"Boavista Porto\",\"price\":4.2},{\"name\":\"Rio Ave FC\",\"price\":2.92},{\"name\":\"Draw\",\"price\":4.2}]}]},{\"key\":\"betonlineag\",\"lastUpdate\":\"2022-11-02T11:55:29Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Boavista Porto\",\"price\":3.05},{\"name\":\"Rio Ave FC\",\"price\":2.35},{\"name\":\"Draw\",\"price\":3.05}]}]}],\"commenceTime\":\"2022-11-06T15:30:00.000Z\",\"completed\":false,\"homeTeam\":\"Rio Ave FC\",\"scores\":null},{\"id\":\"c7ede9e77ceb9af1e3d037b8dcd44116\",\"awayTeam\":\"Casa Pia\",\"bookmakers\":[{\"key\":\"mybookieag\",\"lastUpdate\":\"2022-11-02T11:54:08Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Braga\",\"price\":1.4},{\"name\":\"Casa Pia\",\"price\":7},{\"name\":\"Draw\",\"price\":4.7}]}]},{\"key\":\"betsson\",\"lastUpdate\":\"2022-11-02T11:55:42Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Braga\",\"price\":1.4},{\"name\":\"Casa Pia\",\"price\":7.5},{\"name\":\"Draw\",\"price\":4.75}]}]},{\"key\":\"marathonbet\",\"lastUpdate\":\"2022-11-02T11:55:14Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Braga\",\"price\":1.41},{\"name\":\"Casa Pia\",\"price\":7.3},{\"name\":\"Draw\",\"price\":4.65}]}]},{\"key\":\"williamhill\",\"lastUpdate\":\"2022-11-02T11:55:23Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Braga\",\"price\":1.4},{\"name\":\"Casa Pia\",\"price\":8},{\"name\":\"Draw\",\"price\":4.4}]}]},{\"key\":\"onexbet\",\"lastUpdate\":\"2022-11-02T11:50:17Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Braga\",\"price\":1.4},{\"name\":\"Casa Pia\",\"price\":7.5},{\"name\":\"Draw\",\"price\":4.74}]}]},{\"key\":\"betclic\",\"lastUpdate\":\"2022-11-02T11:56:47Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Braga\",\"price\":1.42},{\"name\":\"Casa Pia\",\"price\":7.3},{\"name\":\"Draw\",\"price\":4.8}]}]},{\"key\":\"pinnacle\",\"lastUpdate\":\"2022-11-02T11:56:47Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Braga\",\"price\":1.4},{\"name\":\"Casa Pia\",\"price\":8.28},{\"name\":\"Draw\",\"price\":4.93}]}]},{\"key\":\"intertops\",\"lastUpdate\":\"2022-11-02T11:56:28Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Braga\",\"price\":1.36},{\"name\":\"Casa Pia\",\"price\":7.25},{\"name\":\"Draw\",\"price\":4.5}]}]},{\"key\":\"unibet_eu\",\"lastUpdate\":\"2022-11-02T11:55:20Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Braga\",\"price\":1.38},{\"name\":\"Casa Pia\",\"price\":7.5},{\"name\":\"Draw\",\"price\":4.7}]}]},{\"key\":\"sport888\",\"lastUpdate\":\"2022-11-02T11:55:23Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Braga\",\"price\":1.34},{\"name\":\"Casa Pia\",\"price\":7.5},{\"name\":\"Draw\",\"price\":4.6}]}]},{\"key\":\"betfair\",\"lastUpdate\":\"2022-11-02T11:58:10Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Braga\",\"price\":1.38},{\"name\":\"Casa Pia\",\"price\":1.04},{\"name\":\"Draw\",\"price\":1.01}]},{\"key\":\"h2h_lay\",\"outcomes\":[{\"name\":\"Braga\",\"price\":1.68},{\"name\":\"Casa Pia\",\"price\":21},{\"name\":\"Draw\",\"price\":950}]}]},{\"key\":\"betonlineag\",\"lastUpdate\":\"2022-11-02T11:55:29Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Braga\",\"price\":1.4},{\"name\":\"Casa Pia\",\"price\":6.9},{\"name\":\"Draw\",\"price\":4.55}]}]}],\"commenceTime\":\"2022-11-06T18:00:00.000Z\",\"completed\":false,\"homeTeam\":\"Braga\",\"scores\":null},{\"id\":\"7ad4bb5d9ab1e9c67014a87403edf80a\",\"awayTeam\":\"Famalicão\",\"bookmakers\":[{\"key\":\"mybookieag\",\"lastUpdate\":\"2022-11-02T11:54:08Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"CS Maritimo\",\"price\":2.8},{\"name\":\"Famalicão\",\"price\":2.55},{\"name\":\"Draw\",\"price\":3.15}]}]},{\"key\":\"betsson\",\"lastUpdate\":\"2022-11-02T11:55:42Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"CS Maritimo\",\"price\":2.8},{\"name\":\"Famalicão\",\"price\":2.55},{\"name\":\"Draw\",\"price\":3.2}]}]},{\"key\":\"marathonbet\",\"lastUpdate\":\"2022-11-02T11:55:14Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"CS Maritimo\",\"price\":2.75},{\"name\":\"Famalicão\",\"price\":2.56},{\"name\":\"Draw\",\"price\":3.26}]}]},{\"key\":\"williamhill\",\"lastUpdate\":\"2022-11-02T11:55:23Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"CS Maritimo\",\"price\":2.75},{\"name\":\"Famalicão\",\"price\":2.6},{\"name\":\"Draw\",\"price\":3.1}]}]},{\"key\":\"onexbet\",\"lastUpdate\":\"2022-11-02T11:50:17Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"CS Maritimo\",\"price\":2.75},{\"name\":\"Famalicão\",\"price\":2.56},{\"name\":\"Draw\",\"price\":3.26}]}]},{\"key\":\"betclic\",\"lastUpdate\":\"2022-11-02T11:56:47Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"CS Maritimo\",\"price\":2.84},{\"name\":\"Famalicão\",\"price\":2.58},{\"name\":\"Draw\",\"price\":3.22}]}]},{\"key\":\"intertops\",\"lastUpdate\":\"2022-11-02T11:56:28Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"CS Maritimo\",\"price\":2.7},{\"name\":\"Famalicão\",\"price\":2.5},{\"name\":\"Draw\",\"price\":3.2}]}]},{\"key\":\"sport888\",\"lastUpdate\":\"2022-11-02T11:55:23Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"CS Maritimo\",\"price\":2.65},{\"name\":\"Famalicão\",\"price\":2.5},{\"name\":\"Draw\",\"price\":3.05}]}]},{\"key\":\"unibet_eu\",\"lastUpdate\":\"2022-11-02T11:55:20Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"CS Maritimo\",\"price\":2.75},{\"name\":\"Famalicão\",\"price\":2.6},{\"name\":\"Draw\",\"price\":3.15}]}]},{\"key\":\"betfair\",\"lastUpdate\":\"2022-11-02T11:58:10Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"CS Maritimo\",\"price\":1.45},{\"name\":\"Famalicão\",\"price\":1.37},{\"name\":\"Draw\",\"price\":1.01}]},{\"key\":\"h2h_lay\",\"outcomes\":[{\"name\":\"CS Maritimo\",\"price\":3.7},{\"name\":\"Famalicão\",\"price\":3.25},{\"name\":\"Draw\",\"price\":950}]}]},{\"key\":\"betonlineag\",\"lastUpdate\":\"2022-11-02T11:55:29Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"CS Maritimo\",\"price\":2.71},{\"name\":\"Famalicão\",\"price\":2.54},{\"name\":\"Draw\",\"price\":3.15}]}]}],\"commenceTime\":\"2022-11-06T18:00:00.000Z\",\"completed\":false,\"homeTeam\":\"CS Maritimo\",\"scores\":null},{\"id\":\"0db34ba8228c8124f7d026b0ce1724d2\",\"awayTeam\":\"Benfica\",\"bookmakers\":[{\"key\":\"onexbet\",\"lastUpdate\":\"2022-11-02T11:50:17Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Benfica\",\"price\":1.31},{\"name\":\"Estoril\",\"price\":9.1},{\"name\":\"Draw\",\"price\":5.4}]}]},{\"key\":\"williamhill\",\"lastUpdate\":\"2022-11-02T11:55:23Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Benfica\",\"price\":1.29},{\"name\":\"Estoril\",\"price\":11},{\"name\":\"Draw\",\"price\":5}]}]},{\"key\":\"mybookieag\",\"lastUpdate\":\"2022-11-02T11:54:08Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Benfica\",\"price\":1.29},{\"name\":\"Estoril\",\"price\":9},{\"name\":\"Draw\",\"price\":5.4}]}]},{\"key\":\"marathonbet\",\"lastUpdate\":\"2022-11-02T11:55:14Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Benfica\",\"price\":1.3},{\"name\":\"Estoril\",\"price\":9.4},{\"name\":\"Draw\",\"price\":5.4}]}]},{\"key\":\"betclic\",\"lastUpdate\":\"2022-11-02T11:56:47Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Benfica\",\"price\":1.31},{\"name\":\"Estoril\",\"price\":9.35},{\"name\":\"Draw\",\"price\":5.6}]}]},{\"key\":\"betsson\",\"lastUpdate\":\"2022-11-02T11:55:42Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Benfica\",\"price\":1.28},{\"name\":\"Estoril\",\"price\":10},{\"name\":\"Draw\",\"price\":5.5}]}]},{\"key\":\"pinnacle\",\"lastUpdate\":\"2022-11-02T11:56:47Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Benfica\",\"price\":1.32},{\"name\":\"Estoril\",\"price\":9.62},{\"name\":\"Draw\",\"price\":5.64}]}]},{\"key\":\"intertops\",\"lastUpdate\":\"2022-11-02T11:56:28Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Benfica\",\"price\":1.28},{\"name\":\"Estoril\",\"price\":8.5},{\"name\":\"Draw\",\"price\":5}]}]},{\"key\":\"unibet_eu\",\"lastUpdate\":\"2022-11-02T11:55:20Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Benfica\",\"price\":1.3},{\"name\":\"Estoril\",\"price\":8.5},{\"name\":\"Draw\",\"price\":5.25}]}]},{\"key\":\"sport888\",\"lastUpdate\":\"2022-11-02T11:55:23Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Benfica\",\"price\":1.27},{\"name\":\"Estoril\",\"price\":8.5},{\"name\":\"Draw\",\"price\":5.1}]}]},{\"key\":\"betfair\",\"lastUpdate\":\"2022-11-02T11:58:10Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Benfica\",\"price\":1.29},{\"name\":\"Estoril\",\"price\":1.06},{\"name\":\"Draw\",\"price\":5.3}]},{\"key\":\"h2h_lay\",\"outcomes\":[{\"name\":\"Benfica\",\"price\":1.38},{\"name\":\"Estoril\",\"price\":28},{\"name\":\"Draw\",\"price\":950}]}]},{\"key\":\"betonlineag\",\"lastUpdate\":\"2022-11-02T11:55:29Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Benfica\",\"price\":1.28},{\"name\":\"Estoril\",\"price\":9.1},{\"name\":\"Draw\",\"price\":5.4}]}]}],\"commenceTime\":\"2022-11-06T20:30:00.000Z\",\"completed\":false,\"homeTeam\":\"Estoril\",\"scores\":null},{\"id\":\"5a673392e3835c2b073ccd468d652593\",\"awayTeam\":\"Santa Clara\",\"bookmakers\":[{\"key\":\"betclic\",\"lastUpdate\":\"2022-11-02T11:56:47Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Chaves\",\"price\":2.37},{\"name\":\"Santa Clara\",\"price\":3.15},{\"name\":\"Draw\",\"price\":3.23}]}]},{\"key\":\"williamhill\",\"lastUpdate\":\"2022-11-02T11:55:23Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Chaves\",\"price\":2.3},{\"name\":\"Santa Clara\",\"price\":3.2},{\"name\":\"Draw\",\"price\":3.1}]}]},{\"key\":\"onexbet\",\"lastUpdate\":\"2022-11-02T11:50:17Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Chaves\",\"price\":2.34},{\"name\":\"Santa Clara\",\"price\":3.16},{\"name\":\"Draw\",\"price\":3.2}]}]},{\"key\":\"marathonbet\",\"lastUpdate\":\"2022-11-02T11:55:14Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Chaves\",\"price\":2.32},{\"name\":\"Santa Clara\",\"price\":3.16},{\"name\":\"Draw\",\"price\":3.2}]}]},{\"key\":\"mybookieag\",\"lastUpdate\":\"2022-11-02T11:54:08Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Chaves\",\"price\":2.33},{\"name\":\"Santa Clara\",\"price\":3.1},{\"name\":\"Draw\",\"price\":3.15}]}]},{\"key\":\"pinnacle\",\"lastUpdate\":\"2022-11-02T11:56:47Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Chaves\",\"price\":2.46},{\"name\":\"Santa Clara\",\"price\":3.39},{\"name\":\"Draw\",\"price\":3.01}]}]},{\"key\":\"unibet_eu\",\"lastUpdate\":\"2022-11-02T11:55:20Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Chaves\",\"price\":2.28},{\"name\":\"Santa Clara\",\"price\":3.25},{\"name\":\"Draw\",\"price\":3.15}]}]},{\"key\":\"sport888\",\"lastUpdate\":\"2022-11-02T11:55:23Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Chaves\",\"price\":2.2},{\"name\":\"Santa Clara\",\"price\":3.15},{\"name\":\"Draw\",\"price\":3.05}]}]},{\"key\":\"betsson\",\"lastUpdate\":\"2022-11-02T11:55:42Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Chaves\",\"price\":2.35},{\"name\":\"Santa Clara\",\"price\":3.1},{\"name\":\"Draw\",\"price\":3.2}]}]},{\"key\":\"intertops\",\"lastUpdate\":\"2022-11-02T11:56:28Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Chaves\",\"price\":2.35},{\"name\":\"Santa Clara\",\"price\":3.2},{\"name\":\"Draw\",\"price\":2.9}]}]},{\"key\":\"betfair\",\"lastUpdate\":\"2022-11-02T11:58:10Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Chaves\",\"price\":2.32},{\"name\":\"Santa Clara\",\"price\":3.2},{\"name\":\"Draw\",\"price\":2.98}]},{\"key\":\"h2h_lay\",\"outcomes\":[{\"name\":\"Chaves\",\"price\":2.58},{\"name\":\"Santa Clara\",\"price\":4.2},{\"name\":\"Draw\",\"price\":3.55}]}]}],\"commenceTime\":\"2022-11-07T20:15:00.000Z\",\"completed\":false,\"homeTeam\":\"Chaves\",\"scores\":null}]");
        Sport futebol = new Sport("Futebol");
        futebol.setId(1);
        SportDAO.store(futebol);
        for (Game game : g) {
            game.generateResult();
            float oddcasa = game.bookmakers.get(0).markets.get(0).outcomes.get(0).price;
            float oddFora = game.bookmakers.get(0).markets.get(0).outcomes.get(1).price;
            float oddEmpate = game.bookmakers.get(0).markets.get(0).outcomes.get(2).price;
            game.getResult().setOddDraw(oddEmpate);
            game.getResult().setOddAwayTeam(oddFora);
            game.getResult().setOddHomeTeam(oddcasa);
            game.setSportId(futebol.getId());
            GameDAO.store(game);
        }

        for (int i = 1; i <= 20; i++){
            model.registerUser("user" + i,"user"+i + "@gmail.com","12345678", String.valueOf(12345678+i),0);
            User user = ((Better)UserDAO.get("user"+i+"@gmail.com"));
            ((Better) user).getWallet().setEuros(new Random().nextFloat()*100);
            ((Better) user).getWallet().setDollars(new Random().nextFloat()*100);
            UserDAO.update(user);
        }
        model.registerUser("especialista","esp@gmail.com","12345678","00000001",1);
        model.registerUser("admin","admin@gmail.com","12345678","000000001",2);

        Bet bet = new Bet(28789753,"538136a794711c8c9bc24b48353c396c",45,0,"user1@gmail.com",false,2,"euros");
        BetDAO.store(bet);

    }

    public static String fetchDatafromAPI(){
        System.out.println("Trying to retrive server data");
        String out = RasBetFacade.getServerData();
        if(out == null) System.out.println("[Network Error] Can't fetch data from \"http://ucras.di.uminho.pt/v1/games/\"");
        return out;
    }
}
