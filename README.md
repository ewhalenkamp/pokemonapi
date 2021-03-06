# Pokémon API

### Developed by: Erik Halenkamp

## About This Project:

This API was created on a Java database I used in previous class projects. It has routing capabilities to list all Pokémon and basic characteristics about them to render the
main list page of my [Pokédex Website](https://github.com/ewhalenkamp/pokedexwebsitev2). Additionally, by specifying '?id=INSTANCE_NUMBER' in the url, you can get detailed
information about each Pokémon instance (instance number is not necessarily the same as their Pokédex number, because different forms of a single Pokémon share the same Pokédex number).

This project was created in Springboot using JDK 1.8 (since that's the only Java version Heroku's buildpack supports).

If you're wondering why there's no commits, I had to reinitialize the repository in the process of hosting it. Very messy process if you don't know what you're doing. Good
learning experience.

### Issues:

Bulbasaur. Pretty much entirely Bulbasaur. I believe there's been some corruption in the .txt files used to generate the database, and for whatever reason Bulbasaur has half
of its information truncated. On the website side, I hard-rendered the Bulbasaur template as a temporary measure. I'll have to look into this later.

Most Gen 7 Pokémon are missing information not because of problems with the API, but because of the lack of serialized information in the format I was already using for every other Pokémon. As a result, much of their information is missing from the base .txt files, and default options have replaced their info in the templates.

~~High latency, check efficiency of data initialization~~
