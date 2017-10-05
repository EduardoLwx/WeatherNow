# WeatherNow

Um aplicativo android simples que fornece a previsão do tempo atual para sua localizaçã.

O projeto é composto por duas activities, a SplashActivity responsável pela obtenção da localização e a WeatherActivity que é responsável pela exibição da previsão obtida. 

O desenvolvimento foi feito no padrão MVP com injeção de dependências.

## Configuração e execução do projeto

Não existe nenhuma dificuldade em rodar o projeto. Todas as pendências encontram-se configuradas nos arquivos do gradle presentes no projeto. 

Para executá-lo, basta usar o Android Studio, baixar todas as pendências e executar o projeto.

## Bibliotecas utilizadas

[Dagger2](https://github.com/google/dagger) - Utilizada na injeção de dependência

[Retrofit2](https://github.com/square/retrofit) - Utilizada na obteção da previsão do tempo

[Gson](https://github.com/google/gson) - Utilidada para conveter o json da previsão em classes Java

[Mockito](https://github.com/mockito/mockito) - Utilizada para mockar as dependências nos testes unitários

## MVP
### Modelos

#### LocalizationService
Composta por três arquivos:

**Localization** -  Responsável por definir a relação entre o modelo(Service) e o presenter(Callback) 
**LocalizationModule** - Responsável por criar o Localization.Service na resolução da dependência do Dagger
**LocalizationService** - implementação concreta do Service.

A requisição da permissão de localização, que deveria ser feita no modelo está sendo feita na view(SplashActivity). Essa decisão foi tomada devido ao fato de a requisição está fortemente ligada a view e o aumento da complexidade do código para que isso fosse realizado no modelo.

#### WeatherService
Composta por 12 arquivos:

**WeatherService** -  Responsável por definir a relação entre o modelo(Service) e o presenter(Callback) 
**WeatherModule** - Responsável por criar o Weather.Service na resolução da dependência do Dagger
**WeatherService** - implementação concreta do Service.
**YahooWeatherService** - interface utilizada pelo Retrofit2 para construção do service.

Os outro 8 arquivos são referentes a estrutura do JSON retornado pelo serviço Yahoo. Estes foram gerados automaticamente utilizando a feramenta online [jsonschema2pojo](http://www.jsonschema2pojo.org/).

### Views e Presenters

#### SplashScreen
Composta por seis arquivos:

*Splash* - Responsável por definir interfaces que devem ser implementadas pela view e presenter
*activity_splash* - View
*SplashActivity* - Camada de acesso a view propriamente
*SplashPresenter* - Presenter da SplashScreen
*SplashModule* - Responsável pela resolução da dependência do Dagger.
*SplashComponent* - Define o Injetor de dependências para a SplashScreen

#### WeatherScreen
Devido a simplicidade não foi construído um presenter para esta view
