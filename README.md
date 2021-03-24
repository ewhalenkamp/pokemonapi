# Pokémon API

### Developed by: Erik Halenkamp

## About This Project:

This API was created on a Java database I used in previous class projects. It has routing capabilities to list all Pokémon and basic characteristics about them to render the
main list page of my [Pokédex Website](https://github.com/ewhalenkamp/pokedexwebsitev2). Additionally, by specifying '?id=INSTANCE_NUMBER' in the url, you can get detailed
information about each Pokémon instance (instance number is not necessarily the same as their Pokédex number, because Pokémon of the same form share the same Pokédex number).

This project was created in Springboot using JDK 1.8 (since that's the only Java version Heroku's buildpack supports).

### Issues:

Bulbasaur. Pretty much entirely Bulbasaur. I believe there's been some corruption in the .txt files used to generate the database, and for whatever reason Bulbasaur has half
of its information truncated. On the website side, I hard-rendered the Bulbasaur template as a temporary measure. I'll have to look into this later.

~~High latency, check efficiency of data initialization~~
