package telegrambot;


import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Program {

    public static void main(String[] args) throws TelegramApiException {
        //System.getProperties().put( "proxySet", "true" );
        //System.getProperties().put( "socksProxyHost", "127.0.0.1" );
        //System.getProperties().put( "socksProxyPort", "9050" );

        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            telegramBotsApi.registerBot(new Bot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}
