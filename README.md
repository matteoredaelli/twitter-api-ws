# twitter-api-ws

FIXME

## Usage

### Setup

file $HOME/.lein/profiles.clj
{:user {:env {
              :twitter-app-consumer-key "mykey"
              :twitter-app-consumer-secret "my-secr"
              :twitter-user-access-token "my-tock"
              :twitter-user-access-token-secret "my-tock-secr"
              }}}


### Running

`lein ring server`

Open with a browser

`http://localhost:3000/index.html`

### Packaging and running as standalone jar

`
lein do clean, ring uberjar
java -jar target/server.jar
`

### Packaging as war

`lein ring uberwar`

## License

GPL V3+
http://www.gnu.org/copyleft/gpl.html

Copyright © 2014 Matteo Redaelli
