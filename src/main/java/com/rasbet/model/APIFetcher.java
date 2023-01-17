package com.rasbet.model;

import com.rasbet.data.*;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class APIFetcher implements Runnable{


    public APIFetcher() {
    }

    public void run(){
        int MINUTES = 10; // The delay in minutes
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                loadData();
            }
        }, 0, 20000);
        //1000*60*MINUTES
    }


    public static String fetchDatafromAPI(){
        return RasBetFacade.getServerData();
    }
    public static void loadData(){
        String data;
        data = fetchDatafromAPI();
        //data="[{\"id\":\"538136a794711c8c9bc24b48353c396c\",\"awayTeam\":\"Portimonense\",\"bookmakers\":[{\"key\":\"betclic\",\"lastUpdate\":\"2022-11-04T21:43:31Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Gil Vicente\",\"price\":3.7},{\"name\":\"Portimonense\",\"price\":4.25},{\"name\":\"Draw\",\"price\":1.75}]}]},{\"key\":\"betsson\",\"lastUpdate\":\"2022-11-04T21:43:32Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Gil Vicente\",\"price\":3.75},{\"name\":\"Portimonense\",\"price\":4.33},{\"name\":\"Draw\",\"price\":1.75}]}]},{\"key\":\"mybookieag\",\"lastUpdate\":\"2022-11-04T21:43:31Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Gil Vicente\",\"price\":3},{\"name\":\"Portimonense\",\"price\":3.65},{\"name\":\"Draw\",\"price\":2.05}]}]},{\"key\":\"williamhill\",\"lastUpdate\":\"2022-11-04T21:43:29Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Gil Vicente\",\"price\":2.25},{\"name\":\"Portimonense\",\"price\":3.2},{\"name\":\"Draw\",\"price\":3.2}]}]},{\"key\":\"betfair\",\"lastUpdate\":\"2022-11-04T21:43:31Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Gil Vicente\",\"price\":3.55},{\"name\":\"Portimonense\",\"price\":4.4},{\"name\":\"Draw\",\"price\":1.96}]},{\"key\":\"h2h_lay\",\"outcomes\":[{\"name\":\"Gil Vicente\",\"price\":3.7},{\"name\":\"Portimonense\",\"price\":4.8},{\"name\":\"Draw\",\"price\":2}]}]},{\"key\":\"pinnacle\",\"lastUpdate\":\"2022-11-04T21:43:30Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Gil Vicente\",\"price\":3.54},{\"name\":\"Portimonense\",\"price\":4.36},{\"name\":\"Draw\",\"price\":1.88}]}]},{\"key\":\"unibet_eu\",\"lastUpdate\":\"2022-11-04T21:43:33Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Gil Vicente\",\"price\":3.65},{\"name\":\"Portimonense\",\"price\":4.25},{\"name\":\"Draw\",\"price\":1.8}]}]},{\"key\":\"matchbook\",\"lastUpdate\":\"2022-11-04T21:43:29Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Gil Vicente\",\"price\":3.75},{\"name\":\"Portimonense\",\"price\":4.3},{\"name\":\"Draw\",\"price\":1.89}]},{\"key\":\"h2h_lay\",\"outcomes\":[{\"name\":\"Gil Vicente\",\"price\":4},{\"name\":\"Portimonense\",\"price\":4.7},{\"name\":\"Draw\",\"price\":1.96}]}]}],\"commenceTime\":\"2022-11-04T20:14:57.000Z\",\"completed\":true,\"homeTeam\":\"Gil Vicente\",\"scores\":\"1x2\"},{\"id\":\"be09fd32746c6c2f11a64157039dc992\",\"awayTeam\":\"Arouca\",\"bookmakers\":[{\"key\":\"betsson\",\"lastUpdate\":\"2022-11-04T21:43:32Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Arouca\",\"price\":4},{\"name\":\"Vizela\",\"price\":1.95},{\"name\":\"Draw\",\"price\":3.3}]}]},{\"key\":\"marathonbet\",\"lastUpdate\":\"2022-11-04T21:43:29Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Arouca\",\"price\":3.98},{\"name\":\"Vizela\",\"price\":1.94},{\"name\":\"Draw\",\"price\":3.4}]}]},{\"key\":\"onexbet\",\"lastUpdate\":\"2022-11-04T21:43:29Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Arouca\",\"price\":3.94},{\"name\":\"Vizela\",\"price\":1.96},{\"name\":\"Draw\",\"price\":3.4}]}]},{\"key\":\"betclic\",\"lastUpdate\":\"2022-11-04T21:43:31Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Arouca\",\"price\":4.17},{\"name\":\"Vizela\",\"price\":1.97},{\"name\":\"Draw\",\"price\":3.32}]}]},{\"key\":\"mybookieag\",\"lastUpdate\":\"2022-11-04T21:43:31Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Arouca\",\"price\":4.1},{\"name\":\"Vizela\",\"price\":1.95},{\"name\":\"Draw\",\"price\":3.3}]}]},{\"key\":\"williamhill\",\"lastUpdate\":\"2022-11-04T21:43:29Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Arouca\",\"price\":4},{\"name\":\"Vizela\",\"price\":1.95},{\"name\":\"Draw\",\"price\":3.25}]}]},{\"key\":\"betfair\",\"lastUpdate\":\"2022-11-04T21:43:31Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Arouca\",\"price\":4.6},{\"name\":\"Vizela\",\"price\":2.02},{\"name\":\"Draw\",\"price\":3.45}]},{\"key\":\"h2h_lay\",\"outcomes\":[{\"name\":\"Arouca\",\"price\":4.8},{\"name\":\"Vizela\",\"price\":2.04},{\"name\":\"Draw\",\"price\":3.6}]}]},{\"key\":\"pinnacle\",\"lastUpdate\":\"2022-11-04T21:43:30Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Arouca\",\"price\":4.45},{\"name\":\"Vizela\",\"price\":1.98},{\"name\":\"Draw\",\"price\":3.33}]}]},{\"key\":\"intertops\",\"lastUpdate\":\"2022-11-04T21:43:31Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Arouca\",\"price\":4.33},{\"name\":\"Vizela\",\"price\":1.91},{\"name\":\"Draw\",\"price\":3.2}]}]},{\"key\":\"unibet_eu\",\"lastUpdate\":\"2022-11-04T21:43:33Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Arouca\",\"price\":4.25},{\"name\":\"Vizela\",\"price\":1.89},{\"name\":\"Draw\",\"price\":3.25}]}]},{\"key\":\"sport888\",\"lastUpdate\":\"2022-11-04T21:43:30Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Arouca\",\"price\":4.2},{\"name\":\"Vizela\",\"price\":1.85},{\"name\":\"Draw\",\"price\":3.15}]}]},{\"key\":\"betonlineag\",\"lastUpdate\":\"2022-11-04T21:43:30Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Arouca\",\"price\":3.75},{\"name\":\"Vizela\",\"price\":1.98},{\"name\":\"Draw\",\"price\":3.25}]}]},{\"key\":\"matchbook\",\"lastUpdate\":\"2022-11-04T21:43:29Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Arouca\",\"price\":4.3},{\"name\":\"Vizela\",\"price\":1.98},{\"name\":\"Draw\",\"price\":3.25}]},{\"key\":\"h2h_lay\",\"outcomes\":[{\"name\":\"Arouca\",\"price\":5},{\"name\":\"Vizela\",\"price\":2.08},{\"name\":\"Draw\",\"price\":3.65}]}]}],\"commenceTime\":\"2022-11-05T15:30:00.000Z\",\"completed\":false,\"homeTeam\":\"Vizela\",\"scores\":null},{\"id\":\"c8d53fadc30913fa823bea3999311b81\",\"awayTeam\":\"Pacos de Ferreira\",\"bookmakers\":[{\"key\":\"betsson\",\"lastUpdate\":\"2022-11-04T21:43:32Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"FC Porto\",\"price\":1.12},{\"name\":\"Pacos de Ferreira\",\"price\":23},{\"name\":\"Draw\",\"price\":8}]}]},{\"key\":\"marathonbet\",\"lastUpdate\":\"2022-11-04T21:43:29Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"FC Porto\",\"price\":1.12},{\"name\":\"Pacos de Ferreira\",\"price\":20},{\"name\":\"Draw\",\"price\":8.6}]}]},{\"key\":\"onexbet\",\"lastUpdate\":\"2022-11-04T21:43:29Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"FC Porto\",\"price\":1.11},{\"name\":\"Pacos de Ferreira\",\"price\":22},{\"name\":\"Draw\",\"price\":8.6}]}]},{\"key\":\"betclic\",\"lastUpdate\":\"2022-11-04T21:43:31Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"FC Porto\",\"price\":1.13},{\"name\":\"Pacos de Ferreira\",\"price\":19.75},{\"name\":\"Draw\",\"price\":8.45}]}]},{\"key\":\"mybookieag\",\"lastUpdate\":\"2022-11-04T21:43:31Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"FC Porto\",\"price\":1.13},{\"name\":\"Pacos de Ferreira\",\"price\":19},{\"name\":\"Draw\",\"price\":8.2}]}]},{\"key\":\"williamhill\",\"lastUpdate\":\"2022-11-04T21:43:29Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"FC Porto\",\"price\":1.11},{\"name\":\"Pacos de Ferreira\",\"price\":23},{\"name\":\"Draw\",\"price\":8}]}]},{\"key\":\"pinnacle\",\"lastUpdate\":\"2022-11-04T21:43:30Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"FC Porto\",\"price\":1.13},{\"name\":\"Pacos de Ferreira\",\"price\":24.3},{\"name\":\"Draw\",\"price\":9.46}]}]},{\"key\":\"betfair\",\"lastUpdate\":\"2022-11-04T21:43:31Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"FC Porto\",\"price\":1.13},{\"name\":\"Pacos de Ferreira\",\"price\":30},{\"name\":\"Draw\",\"price\":10.5}]},{\"key\":\"h2h_lay\",\"outcomes\":[{\"name\":\"FC Porto\",\"price\":1.15},{\"name\":\"Pacos de Ferreira\",\"price\":34},{\"name\":\"Draw\",\"price\":11.5}]}]},{\"key\":\"intertops\",\"lastUpdate\":\"2022-11-04T21:43:31Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"FC Porto\",\"price\":1.1},{\"name\":\"Pacos de Ferreira\",\"price\":21},{\"name\":\"Draw\",\"price\":8}]}]},{\"key\":\"unibet_eu\",\"lastUpdate\":\"2022-11-04T21:43:33Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"FC Porto\",\"price\":1.12},{\"name\":\"Pacos de Ferreira\",\"price\":18},{\"name\":\"Draw\",\"price\":8.5}]}]},{\"key\":\"sport888\",\"lastUpdate\":\"2022-11-04T21:43:30Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"FC Porto\",\"price\":1.09},{\"name\":\"Pacos de Ferreira\",\"price\":17},{\"name\":\"Draw\",\"price\":8}]}]},{\"key\":\"betonlineag\",\"lastUpdate\":\"2022-11-04T21:43:30Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"FC Porto\",\"price\":1.1},{\"name\":\"Pacos de Ferreira\",\"price\":19},{\"name\":\"Draw\",\"price\":8.19}]}]},{\"key\":\"matchbook\",\"lastUpdate\":\"2022-11-04T21:43:29Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"FC Porto\",\"price\":1.13},{\"name\":\"Pacos de Ferreira\",\"price\":26},{\"name\":\"Draw\",\"price\":9.6}]},{\"key\":\"h2h_lay\",\"outcomes\":[{\"name\":\"FC Porto\",\"price\":1.15},{\"name\":\"Pacos de Ferreira\",\"price\":34},{\"name\":\"Draw\",\"price\":12.5}]}]}],\"commenceTime\":\"2022-11-05T18:00:00.000Z\",\"completed\":false,\"homeTeam\":\"FC Porto\",\"scores\":null},{\"id\":\"6ae5f22198593f180590bf8161bbf910\",\"awayTeam\":\"Vitória SC\",\"bookmakers\":[{\"key\":\"mybookieag\",\"lastUpdate\":\"2022-11-04T21:43:31Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Sporting Lisbon\",\"price\":1.33},{\"name\":\"Vitória SC\",\"price\":8.8},{\"name\":\"Draw\",\"price\":5.2}]}]},{\"key\":\"betsson\",\"lastUpdate\":\"2022-11-04T21:43:32Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Sporting Lisbon\",\"price\":1.35},{\"name\":\"Vitória SC\",\"price\":8.5},{\"name\":\"Draw\",\"price\":5}]}]},{\"key\":\"betclic\",\"lastUpdate\":\"2022-11-04T21:43:31Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Sporting Lisbon\",\"price\":1.34},{\"name\":\"Vitória SC\",\"price\":9.05},{\"name\":\"Draw\",\"price\":5.2}]}]},{\"key\":\"marathonbet\",\"lastUpdate\":\"2022-11-04T21:43:29Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Sporting Lisbon\",\"price\":1.32},{\"name\":\"Vitória SC\",\"price\":9.2},{\"name\":\"Draw\",\"price\":5.15}]}]},{\"key\":\"williamhill\",\"lastUpdate\":\"2022-11-04T21:43:29Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Sporting Lisbon\",\"price\":1.33},{\"name\":\"Vitória SC\",\"price\":10},{\"name\":\"Draw\",\"price\":4.6}]}]},{\"key\":\"intertops\",\"lastUpdate\":\"2022-11-04T21:43:31Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Sporting Lisbon\",\"price\":1.33},{\"name\":\"Vitória SC\",\"price\":9},{\"name\":\"Draw\",\"price\":5}]}]},{\"key\":\"unibet_eu\",\"lastUpdate\":\"2022-11-04T21:43:33Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Sporting Lisbon\",\"price\":1.33},{\"name\":\"Vitória SC\",\"price\":8.5},{\"name\":\"Draw\",\"price\":5.1}]}]},{\"key\":\"sport888\",\"lastUpdate\":\"2022-11-04T21:43:30Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Sporting Lisbon\",\"price\":1.29},{\"name\":\"Vitória SC\",\"price\":8},{\"name\":\"Draw\",\"price\":5}]}]},{\"key\":\"betfair\",\"lastUpdate\":\"2022-11-04T21:43:31Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Sporting Lisbon\",\"price\":1.37},{\"name\":\"Vitória SC\",\"price\":10},{\"name\":\"Draw\",\"price\":5.6}]},{\"key\":\"h2h_lay\",\"outcomes\":[{\"name\":\"Sporting Lisbon\",\"price\":1.38},{\"name\":\"Vitória SC\",\"price\":11},{\"name\":\"Draw\",\"price\":6.2}]}]},{\"key\":\"pinnacle\",\"lastUpdate\":\"2022-11-04T21:43:30Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Sporting Lisbon\",\"price\":1.35},{\"name\":\"Vitória SC\",\"price\":9.5},{\"name\":\"Draw\",\"price\":5.32}]}]},{\"key\":\"betonlineag\",\"lastUpdate\":\"2022-11-04T21:43:30Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Sporting Lisbon\",\"price\":1.33},{\"name\":\"Vitória SC\",\"price\":8.1},{\"name\":\"Draw\",\"price\":5}]}]},{\"key\":\"matchbook\",\"lastUpdate\":\"2022-11-04T21:43:29Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Sporting Lisbon\",\"price\":1.35},{\"name\":\"Vitória SC\",\"price\":9.8},{\"name\":\"Draw\",\"price\":5.5}]},{\"key\":\"h2h_lay\",\"outcomes\":[{\"name\":\"Sporting Lisbon\",\"price\":1.4},{\"name\":\"Vitória SC\",\"price\":11},{\"name\":\"Draw\",\"price\":6.2}]}]}],\"commenceTime\":\"2022-11-05T20:30:00.000Z\",\"completed\":false,\"homeTeam\":\"Sporting Lisbon\",\"scores\":null},{\"id\":\"72cdea24cebb5465e577b8fe3f3cdfeb\",\"awayTeam\":\"Boavista Porto\",\"bookmakers\":[{\"key\":\"mybookieag\",\"lastUpdate\":\"2022-11-04T21:43:31Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Boavista Porto\",\"price\":3.1},{\"name\":\"Rio Ave FC\",\"price\":2.41},{\"name\":\"Draw\",\"price\":3.1}]}]},{\"key\":\"betsson\",\"lastUpdate\":\"2022-11-04T21:43:32Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Boavista Porto\",\"price\":3},{\"name\":\"Rio Ave FC\",\"price\":2.4},{\"name\":\"Draw\",\"price\":3.2}]}]},{\"key\":\"marathonbet\",\"lastUpdate\":\"2022-11-04T21:43:29Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Boavista Porto\",\"price\":3.08},{\"name\":\"Rio Ave FC\",\"price\":2.4},{\"name\":\"Draw\",\"price\":3.12}]}]},{\"key\":\"williamhill\",\"lastUpdate\":\"2022-11-04T21:43:29Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Boavista Porto\",\"price\":3.1},{\"name\":\"Rio Ave FC\",\"price\":2.4},{\"name\":\"Draw\",\"price\":3}]}]},{\"key\":\"onexbet\",\"lastUpdate\":\"2022-11-04T21:43:29Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Boavista Porto\",\"price\":3.08},{\"name\":\"Rio Ave FC\",\"price\":2.42},{\"name\":\"Draw\",\"price\":3.12}]}]},{\"key\":\"betclic\",\"lastUpdate\":\"2022-11-04T21:43:31Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Boavista Porto\",\"price\":3.1},{\"name\":\"Rio Ave FC\",\"price\":2.43},{\"name\":\"Draw\",\"price\":3.15}]}]},{\"key\":\"pinnacle\",\"lastUpdate\":\"2022-11-04T21:43:30Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Boavista Porto\",\"price\":3.13},{\"name\":\"Rio Ave FC\",\"price\":2.52},{\"name\":\"Draw\",\"price\":3.22}]}]},{\"key\":\"intertops\",\"lastUpdate\":\"2022-11-04T21:43:31Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Boavista Porto\",\"price\":2.95},{\"name\":\"Rio Ave FC\",\"price\":2.4},{\"name\":\"Draw\",\"price\":3.05}]}]},{\"key\":\"unibet_eu\",\"lastUpdate\":\"2022-11-04T21:43:33Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Boavista Porto\",\"price\":3},{\"name\":\"Rio Ave FC\",\"price\":2.43},{\"name\":\"Draw\",\"price\":3.1}]}]},{\"key\":\"sport888\",\"lastUpdate\":\"2022-11-04T21:43:30Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Boavista Porto\",\"price\":2.9},{\"name\":\"Rio Ave FC\",\"price\":2.38},{\"name\":\"Draw\",\"price\":3}]}]},{\"key\":\"betfair\",\"lastUpdate\":\"2022-11-04T21:43:31Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Boavista Porto\",\"price\":3.1},{\"name\":\"Rio Ave FC\",\"price\":2.46},{\"name\":\"Draw\",\"price\":3.1}]},{\"key\":\"h2h_lay\",\"outcomes\":[{\"name\":\"Boavista Porto\",\"price\":3.45},{\"name\":\"Rio Ave FC\",\"price\":2.78},{\"name\":\"Draw\",\"price\":3.45}]}]},{\"key\":\"betonlineag\",\"lastUpdate\":\"2022-11-04T21:43:30Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Boavista Porto\",\"price\":3},{\"name\":\"Rio Ave FC\",\"price\":2.38},{\"name\":\"Draw\",\"price\":3.05}]}]},{\"key\":\"matchbook\",\"lastUpdate\":\"2022-11-04T21:43:29Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Boavista Porto\",\"price\":2.94},{\"name\":\"Rio Ave FC\",\"price\":2.34},{\"name\":\"Draw\",\"price\":2.98}]},{\"key\":\"h2h_lay\",\"outcomes\":[{\"name\":\"Boavista Porto\",\"price\":3.65},{\"name\":\"Rio Ave FC\",\"price\":2.82},{\"name\":\"Draw\",\"price\":3.6}]}]}],\"commenceTime\":\"2022-11-06T15:30:00.000Z\",\"completed\":false,\"homeTeam\":\"Rio Ave FC\",\"scores\":null},{\"id\":\"c7ede9e77ceb9af1e3d037b8dcd44116\",\"awayTeam\":\"Casa Pia\",\"bookmakers\":[{\"key\":\"mybookieag\",\"lastUpdate\":\"2022-11-04T21:43:31Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Braga\",\"price\":1.41},{\"name\":\"Casa Pia\",\"price\":7.2},{\"name\":\"Draw\",\"price\":4.7}]}]},{\"key\":\"betsson\",\"lastUpdate\":\"2022-11-04T21:43:32Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Braga\",\"price\":1.4},{\"name\":\"Casa Pia\",\"price\":7.5},{\"name\":\"Draw\",\"price\":4.75}]}]},{\"key\":\"marathonbet\",\"lastUpdate\":\"2022-11-04T21:43:29Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Braga\",\"price\":1.41},{\"name\":\"Casa Pia\",\"price\":7.3},{\"name\":\"Draw\",\"price\":4.65}]}]},{\"key\":\"williamhill\",\"lastUpdate\":\"2022-11-04T21:43:29Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Braga\",\"price\":1.4},{\"name\":\"Casa Pia\",\"price\":8},{\"name\":\"Draw\",\"price\":4.4}]}]},{\"key\":\"onexbet\",\"lastUpdate\":\"2022-11-04T21:43:29Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Braga\",\"price\":1.42},{\"name\":\"Casa Pia\",\"price\":7.2},{\"name\":\"Draw\",\"price\":4.64}]}]},{\"key\":\"betclic\",\"lastUpdate\":\"2022-11-04T21:43:31Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Braga\",\"price\":1.42},{\"name\":\"Casa Pia\",\"price\":7.3},{\"name\":\"Draw\",\"price\":4.8}]}]},{\"key\":\"pinnacle\",\"lastUpdate\":\"2022-11-04T21:43:30Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Braga\",\"price\":1.42},{\"name\":\"Casa Pia\",\"price\":8.07},{\"name\":\"Draw\",\"price\":4.88}]}]},{\"key\":\"intertops\",\"lastUpdate\":\"2022-11-04T21:43:31Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Braga\",\"price\":1.36},{\"name\":\"Casa Pia\",\"price\":7.25},{\"name\":\"Draw\",\"price\":4.5}]}]},{\"key\":\"unibet_eu\",\"lastUpdate\":\"2022-11-04T21:43:33Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Braga\",\"price\":1.4},{\"name\":\"Casa Pia\",\"price\":7.5},{\"name\":\"Draw\",\"price\":4.6}]}]},{\"key\":\"sport888\",\"lastUpdate\":\"2022-11-04T21:43:30Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Braga\",\"price\":1.36},{\"name\":\"Casa Pia\",\"price\":7},{\"name\":\"Draw\",\"price\":4.5}]}]},{\"key\":\"betfair\",\"lastUpdate\":\"2022-11-04T21:43:31Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Braga\",\"price\":1.41},{\"name\":\"Casa Pia\",\"price\":7.4},{\"name\":\"Draw\",\"price\":4.9}]},{\"key\":\"h2h_lay\",\"outcomes\":[{\"name\":\"Braga\",\"price\":1.49},{\"name\":\"Casa Pia\",\"price\":9.6},{\"name\":\"Draw\",\"price\":5.7}]}]},{\"key\":\"betonlineag\",\"lastUpdate\":\"2022-11-04T21:43:30Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Braga\",\"price\":1.4},{\"name\":\"Casa Pia\",\"price\":6.9},{\"name\":\"Draw\",\"price\":4.55}]}]},{\"key\":\"matchbook\",\"lastUpdate\":\"2022-11-04T21:43:29Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Braga\",\"price\":1.39},{\"name\":\"Casa Pia\",\"price\":7.4},{\"name\":\"Draw\",\"price\":4.5}]},{\"key\":\"h2h_lay\",\"outcomes\":[{\"name\":\"Braga\",\"price\":1.52},{\"name\":\"Casa Pia\",\"price\":9.8},{\"name\":\"Draw\",\"price\":5.9}]}]}],\"commenceTime\":\"2022-11-06T18:00:00.000Z\",\"completed\":false,\"homeTeam\":\"Braga\",\"scores\":null},{\"id\":\"7ad4bb5d9ab1e9c67014a87403edf80a\",\"awayTeam\":\"Famalicão\",\"bookmakers\":[{\"key\":\"mybookieag\",\"lastUpdate\":\"2022-11-04T21:43:31Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"CS Maritimo\",\"price\":2.75},{\"name\":\"Famalicão\",\"price\":2.65},{\"name\":\"Draw\",\"price\":3.15}]}]},{\"key\":\"betsson\",\"lastUpdate\":\"2022-11-04T21:43:32Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"CS Maritimo\",\"price\":2.75},{\"name\":\"Famalicão\",\"price\":2.65},{\"name\":\"Draw\",\"price\":3.1}]}]},{\"key\":\"marathonbet\",\"lastUpdate\":\"2022-11-04T21:43:29Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"CS Maritimo\",\"price\":2.78},{\"name\":\"Famalicão\",\"price\":2.54},{\"name\":\"Draw\",\"price\":3.25}]}]},{\"key\":\"williamhill\",\"lastUpdate\":\"2022-11-04T21:43:29Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"CS Maritimo\",\"price\":2.7},{\"name\":\"Famalicão\",\"price\":2.62},{\"name\":\"Draw\",\"price\":3.1}]}]},{\"key\":\"onexbet\",\"lastUpdate\":\"2022-11-04T21:43:29Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"CS Maritimo\",\"price\":2.78},{\"name\":\"Famalicão\",\"price\":2.54},{\"name\":\"Draw\",\"price\":3.24}]}]},{\"key\":\"betclic\",\"lastUpdate\":\"2022-11-04T21:43:31Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"CS Maritimo\",\"price\":2.77},{\"name\":\"Famalicão\",\"price\":2.66},{\"name\":\"Draw\",\"price\":3.2}]}]},{\"key\":\"intertops\",\"lastUpdate\":\"2022-11-04T21:43:31Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"CS Maritimo\",\"price\":2.75},{\"name\":\"Famalicão\",\"price\":2.5},{\"name\":\"Draw\",\"price\":3.05}]}]},{\"key\":\"sport888\",\"lastUpdate\":\"2022-11-04T21:43:30Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"CS Maritimo\",\"price\":2.7},{\"name\":\"Famalicão\",\"price\":2.48},{\"name\":\"Draw\",\"price\":3.05}]}]},{\"key\":\"unibet_eu\",\"lastUpdate\":\"2022-11-04T21:43:33Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"CS Maritimo\",\"price\":2.8},{\"name\":\"Famalicão\",\"price\":2.55},{\"name\":\"Draw\",\"price\":3.15}]}]},{\"key\":\"betfair\",\"lastUpdate\":\"2022-11-04T21:43:31Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"CS Maritimo\",\"price\":2.78},{\"name\":\"Famalicão\",\"price\":2.64},{\"name\":\"Draw\",\"price\":3.2}]},{\"key\":\"h2h_lay\",\"outcomes\":[{\"name\":\"CS Maritimo\",\"price\":3.1},{\"name\":\"Famalicão\",\"price\":3.05},{\"name\":\"Draw\",\"price\":3.7}]}]},{\"key\":\"betonlineag\",\"lastUpdate\":\"2022-11-04T21:43:30Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"CS Maritimo\",\"price\":2.73},{\"name\":\"Famalicão\",\"price\":2.54},{\"name\":\"Draw\",\"price\":3.15}]}]},{\"key\":\"pinnacle\",\"lastUpdate\":\"2022-11-04T21:43:30Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"CS Maritimo\",\"price\":2.93},{\"name\":\"Famalicão\",\"price\":2.67},{\"name\":\"Draw\",\"price\":3.21}]}]},{\"key\":\"matchbook\",\"lastUpdate\":\"2022-11-04T21:43:29Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"CS Maritimo\",\"price\":2.7},{\"name\":\"Famalicão\",\"price\":2.48},{\"name\":\"Draw\",\"price\":2.98}]},{\"key\":\"h2h_lay\",\"outcomes\":[{\"name\":\"CS Maritimo\",\"price\":3.35},{\"name\":\"Famalicão\",\"price\":3.05},{\"name\":\"Draw\",\"price\":3.7}]}]}],\"commenceTime\":\"2022-11-06T18:00:00.000Z\",\"completed\":false,\"homeTeam\":\"CS Maritimo\",\"scores\":null},{\"id\":\"0db34ba8228c8124f7d026b0ce1724d2\",\"awayTeam\":\"Benfica\",\"bookmakers\":[{\"key\":\"onexbet\",\"lastUpdate\":\"2022-11-04T21:43:29Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Benfica\",\"price\":1.28},{\"name\":\"Estoril\",\"price\":9.9},{\"name\":\"Draw\",\"price\":5.7}]}]},{\"key\":\"williamhill\",\"lastUpdate\":\"2022-11-04T21:43:29Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Benfica\",\"price\":1.29},{\"name\":\"Estoril\",\"price\":11},{\"name\":\"Draw\",\"price\":5}]}]},{\"key\":\"mybookieag\",\"lastUpdate\":\"2022-11-04T21:43:31Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Benfica\",\"price\":1.3},{\"name\":\"Estoril\",\"price\":9.2},{\"name\":\"Draw\",\"price\":5.4}]}]},{\"key\":\"marathonbet\",\"lastUpdate\":\"2022-11-04T21:43:29Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Benfica\",\"price\":1.27},{\"name\":\"Estoril\",\"price\":10.25},{\"name\":\"Draw\",\"price\":5.7}]}]},{\"key\":\"betclic\",\"lastUpdate\":\"2022-11-04T21:43:31Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Benfica\",\"price\":1.31},{\"name\":\"Estoril\",\"price\":9.35},{\"name\":\"Draw\",\"price\":5.6}]}]},{\"key\":\"betsson\",\"lastUpdate\":\"2022-11-04T21:43:32Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Benfica\",\"price\":1.28},{\"name\":\"Estoril\",\"price\":10},{\"name\":\"Draw\",\"price\":5.5}]}]},{\"key\":\"pinnacle\",\"lastUpdate\":\"2022-11-04T21:43:30Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Benfica\",\"price\":1.3},{\"name\":\"Estoril\",\"price\":10.48},{\"name\":\"Draw\",\"price\":5.99}]}]},{\"key\":\"intertops\",\"lastUpdate\":\"2022-11-04T21:43:31Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Benfica\",\"price\":1.25},{\"name\":\"Estoril\",\"price\":9.5},{\"name\":\"Draw\",\"price\":5.5}]}]},{\"key\":\"unibet_eu\",\"lastUpdate\":\"2022-11-04T21:43:33Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Benfica\",\"price\":1.29},{\"name\":\"Estoril\",\"price\":9},{\"name\":\"Draw\",\"price\":5.5}]}]},{\"key\":\"sport888\",\"lastUpdate\":\"2022-11-04T21:43:30Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Benfica\",\"price\":1.25},{\"name\":\"Estoril\",\"price\":9},{\"name\":\"Draw\",\"price\":5.3}]}]},{\"key\":\"betfair\",\"lastUpdate\":\"2022-11-04T21:43:31Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Benfica\",\"price\":1.29},{\"name\":\"Estoril\",\"price\":10.5},{\"name\":\"Draw\",\"price\":6}]},{\"key\":\"h2h_lay\",\"outcomes\":[{\"name\":\"Benfica\",\"price\":1.33},{\"name\":\"Estoril\",\"price\":13},{\"name\":\"Draw\",\"price\":6.8}]}]},{\"key\":\"betonlineag\",\"lastUpdate\":\"2022-11-04T21:43:30Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Benfica\",\"price\":1.27},{\"name\":\"Estoril\",\"price\":9.4},{\"name\":\"Draw\",\"price\":5.4}]}]},{\"key\":\"matchbook\",\"lastUpdate\":\"2022-11-04T21:43:29Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Benfica\",\"price\":1.26},{\"name\":\"Estoril\",\"price\":10.5},{\"name\":\"Draw\",\"price\":5.6}]},{\"key\":\"h2h_lay\",\"outcomes\":[{\"name\":\"Benfica\",\"price\":1.36},{\"name\":\"Estoril\",\"price\":14.5},{\"name\":\"Draw\",\"price\":7.4}]}]}],\"commenceTime\":\"2022-11-06T20:30:00.000Z\",\"completed\":false,\"homeTeam\":\"Estoril\",\"scores\":null},{\"id\":\"5a673392e3835c2b073ccd468d652593\",\"awayTeam\":\"Santa Clara\",\"bookmakers\":[{\"key\":\"betclic\",\"lastUpdate\":\"2022-11-04T21:43:31Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Chaves\",\"price\":2.38},{\"name\":\"Santa Clara\",\"price\":3.2},{\"name\":\"Draw\",\"price\":3.17}]}]},{\"key\":\"williamhill\",\"lastUpdate\":\"2022-11-04T21:43:29Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Chaves\",\"price\":2.3},{\"name\":\"Santa Clara\",\"price\":3.2},{\"name\":\"Draw\",\"price\":3.1}]}]},{\"key\":\"onexbet\",\"lastUpdate\":\"2022-11-04T21:43:29Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Chaves\",\"price\":2.33},{\"name\":\"Santa Clara\",\"price\":3.17},{\"name\":\"Draw\",\"price\":3.2}]}]},{\"key\":\"marathonbet\",\"lastUpdate\":\"2022-11-04T21:43:29Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Chaves\",\"price\":2.31},{\"name\":\"Santa Clara\",\"price\":3.18},{\"name\":\"Draw\",\"price\":3.2}]}]},{\"key\":\"mybookieag\",\"lastUpdate\":\"2022-11-04T21:43:31Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Chaves\",\"price\":2.35},{\"name\":\"Santa Clara\",\"price\":3.15},{\"name\":\"Draw\",\"price\":3.15}]}]},{\"key\":\"pinnacle\",\"lastUpdate\":\"2022-11-04T21:43:30Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Chaves\",\"price\":2.42},{\"name\":\"Santa Clara\",\"price\":3.48},{\"name\":\"Draw\",\"price\":3}]}]},{\"key\":\"unibet_eu\",\"lastUpdate\":\"2022-11-04T21:43:33Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Chaves\",\"price\":2.35},{\"name\":\"Santa Clara\",\"price\":3.25},{\"name\":\"Draw\",\"price\":2.95}]}]},{\"key\":\"sport888\",\"lastUpdate\":\"2022-11-04T21:43:30Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Chaves\",\"price\":2.3},{\"name\":\"Santa Clara\",\"price\":3.2},{\"name\":\"Draw\",\"price\":2.85}]}]},{\"key\":\"betsson\",\"lastUpdate\":\"2022-11-04T21:43:32Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Chaves\",\"price\":2.35},{\"name\":\"Santa Clara\",\"price\":3.1},{\"name\":\"Draw\",\"price\":3.2}]}]},{\"key\":\"intertops\",\"lastUpdate\":\"2022-11-04T21:43:31Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Chaves\",\"price\":2.3},{\"name\":\"Santa Clara\",\"price\":3.3},{\"name\":\"Draw\",\"price\":2.85}]}]},{\"key\":\"betfair\",\"lastUpdate\":\"2022-11-04T21:43:31Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Chaves\",\"price\":2.38},{\"name\":\"Santa Clara\",\"price\":3.35},{\"name\":\"Draw\",\"price\":3.25}]},{\"key\":\"h2h_lay\",\"outcomes\":[{\"name\":\"Chaves\",\"price\":2.54},{\"name\":\"Santa Clara\",\"price\":3.65},{\"name\":\"Draw\",\"price\":3.35}]}]},{\"key\":\"betonlineag\",\"lastUpdate\":\"2022-11-04T21:43:30Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Chaves\",\"price\":2.33},{\"name\":\"Santa Clara\",\"price\":3.05},{\"name\":\"Draw\",\"price\":3.1}]}]}],\"commenceTime\":\"2022-11-07T20:15:00.000Z\",\"completed\":false,\"homeTeam\":\"Chaves\",\"scores\":null},{\"id\":\"3ec6ffeee46fb782d327e8b42463b00c\",\"awayTeam\":\"Rio Ave FC\",\"bookmakers\":[{\"key\":\"mybookieag\",\"lastUpdate\":\"2022-11-04T21:43:31Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Arouca\",\"price\":2.16},{\"name\":\"Rio Ave FC\",\"price\":3.35},{\"name\":\"Draw\",\"price\":3.15}]}]},{\"key\":\"betsson\",\"lastUpdate\":\"2022-11-04T21:43:32Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Arouca\",\"price\":2.25},{\"name\":\"Rio Ave FC\",\"price\":3.4},{\"name\":\"Draw\",\"price\":3.1}]}]},{\"key\":\"marathonbet\",\"lastUpdate\":\"2022-11-04T21:43:29Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Arouca\",\"price\":2.2},{\"name\":\"Rio Ave FC\",\"price\":3.32},{\"name\":\"Draw\",\"price\":3.28}]}]},{\"key\":\"onexbet\",\"lastUpdate\":\"2022-11-04T21:43:29Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Arouca\",\"price\":2.22},{\"name\":\"Rio Ave FC\",\"price\":3.3},{\"name\":\"Draw\",\"price\":3.28}]}]},{\"key\":\"betclic\",\"lastUpdate\":\"2022-11-04T21:43:31Z\",\"markets\":[{\"key\":\"h2h\",\"outcomes\":[{\"name\":\"Arouca\",\"price\":2.21},{\"name\":\"Rio Ave FC\",\"price\":3.45},{\"name\":\"Draw\",\"price\":3.25}]}]}],\"commenceTime\":\"2022-11-12T18:00:00.000Z\",\"completed\":false,\"homeTeam\":\"Arouca\",\"scores\":null}]";
        List<Game> g = RasBetFacade.parseJson(data);
        Sport futebol = new Sport("Futebol");
        futebol.setId(1);
        SportDAO.store(futebol);
        for (Game game : g) {
            if(!RasBetFacade.gameExists(game.getId())) {
                game.generateResult();
                float oddcasa = game.bookmakers.get(0).markets.get(0).outcomes.get(0).price;
                float oddFora = game.bookmakers.get(0).markets.get(0).outcomes.get(1).price;
                float oddEmpate = game.bookmakers.get(0).markets.get(0).outcomes.get(2).price;
                game.getResult().setOddDraw(oddEmpate);
                game.getResult().setOddAwayTeam(oddFora);
                game.getResult().setOddHomeTeam(oddcasa);
                game.setSportId(futebol.getId());
                ResultDAO.store(game.getResult());
            } else game.setResult(GameDAO.get(game.getId()).getResult());
            // Se já se encontrar guardado atualizar o seu estado
            if(!GameDAO.store(game)) {
                GameDAO.updateStatusGame(game);

            } else {
                ResultDAO.update(game.getResult());
            }
        }
        for (Game game : g) {
            if(game.getScores() != null) {
                String[] strArray = game.getScores().split("x");
                int scoreHomeTeam = Integer.parseInt(strArray[0]), scoreAwayTeam = Integer.parseInt(strArray[1]);
                if (scoreAwayTeam < scoreHomeTeam) game.getResult().setwinningTeam(game.getHomeTeam());
                else if (scoreAwayTeam == scoreHomeTeam) game.getResult().setwinningTeam("draw");
                else game.getResult().setwinningTeam(game.getAwayTeam());
                game.getResult().setScores(game.getScores());
                List<Bet> bets = BetDAO.getBetsByGameId(game.getId());
                int multipleIdanterior = -1;
                boolean apostaMultiplaCompleta = true;
                for (Bet b : bets) {
                    if (multipleIdanterior != b.getMultipleId() && b.getBetState() != 1) {
                        if(apostaMultiplaCompleta) {
                            b.setBetState(1);
                            RasBetFacade.updateBet(b);
                            switch (b.getBettedTeam()) {
                                case 0:
                                    if (game.getHomeTeam().equalsIgnoreCase(game.getResult().getwinningTeam())) {
                                        if (b.getCurrency().equalsIgnoreCase("euros")) {
                                            RasBetFacade.addMovementEuros(b.getPossibleGain(), b.getEmail(), "Ganho Aposta");
                                        } else {
                                            RasBetFacade.addMovementDollars(b.getPossibleGain(), b.getEmail(), "Ganho Aposta");
                                        }
                                        RasBetFacade.notificaUser(RasBetFacade.getEmailAuthenticatedUser(), "Ganhou " + b.getPossibleGain() + " na sua aposta no equipa " + game.getHomeTeam() + " no jogo " + game.getHomeTeam() + " x " + game.getAwayTeam() + " em " + game.getCommenceTime());
                                    } else {
                                        RasBetFacade.notificaUser(RasBetFacade.getEmailAuthenticatedUser(), "Perdeu a sua aposta na equipa " + game.getHomeTeam() + " no jogo " + game.getHomeTeam() + " x " + game.getAwayTeam() + " em " + game.getCommenceTime());
                                    }
                                    break;
                                case 1:
                                    if (game.getAwayTeam().equalsIgnoreCase(game.getResult().getwinningTeam())) {
                                        if (b.getCurrency().equalsIgnoreCase("euros")) {
                                            RasBetFacade.addMovementEuros(b.getPossibleGain(), b.getEmail(), "Ganho Aposta");
                                        } else
                                            RasBetFacade.addMovementDollars(b.getPossibleGain(), b.getEmail(), "Ganho Aposta");
                                        RasBetFacade.notificaUser(RasBetFacade.getEmailAuthenticatedUser(), "Ganhou " + b.getPossibleGain() + " na sua aposta no equipa " + game.getAwayTeam() + " no jogo " + game.getHomeTeam() + " x " + game.getAwayTeam() + " em " + game.getCommenceTime());
                                    } else {
                                        RasBetFacade.notificaUser(RasBetFacade.getEmailAuthenticatedUser(), "Perdeu a sua aposta na equipa " + game.getAwayTeam() + " no jogo " + game.getHomeTeam() + " x " + game.getAwayTeam() + " em " + game.getCommenceTime());
                                    }
                                    break;
                                default:
                                    if (game.getResult().getwinningTeam().equalsIgnoreCase("draw")) {
                                        if (b.getCurrency().equalsIgnoreCase("euros")) {
                                            RasBetFacade.addMovementEuros(b.getPossibleGain(), b.getEmail(), "Ganho Aposta");
                                        } else
                                            RasBetFacade.addMovementDollars(b.getPossibleGain(), b.getEmail(), "Ganho Aposta");
                                        RasBetFacade.notificaUser(RasBetFacade.getEmailAuthenticatedUser(), "Ganhou " + b.getPossibleGain() + " na sua aposta no empate no jogo " + game.getHomeTeam() + " x " + game.getAwayTeam() + " em " + game.getCommenceTime());
                                    } else {
                                        RasBetFacade.notificaUser(RasBetFacade.getEmailAuthenticatedUser(), "Perdeu a sua aposta no empate no jogo " + game.getHomeTeam() + " x " + game.getAwayTeam() + " em " + game.getCommenceTime());
                                    }
                                    break;
                            }
                        }else apostaMultiplaCompleta = true;
                    } else {
                        if(!RasBetFacade.getGame(b.getGameId()).getCompleted())
                            apostaMultiplaCompleta = false;
                    }
                    multipleIdanterior = b.getMultipleId();
                }
            }
        }

    }
}