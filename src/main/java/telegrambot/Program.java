package telegrambot;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import repository.AnimalsRepository;
import repository.DescriptionsRepository;

@SpringBootApplication(
        scanBasePackageClasses = {
                AnimalsRepository.class,
                DescriptionsRepository.class
        }
)
@ComponentScan(basePackageClasses =  {
        AnimalsRepository.class,
        DescriptionsRepository.class
})
public class Program {

    public static void main(String[] args) throws TelegramApiException {
        SpringApplication.run(Program.class, args);
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
