package com.erikhalenkamp.pokemonapi;
import java.util.*;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class DataClass {
    
    private ArrayList<BaseAbility> abilityList = new ArrayList<BaseAbility>();
    private ArrayList<BaseMove> moveList = new ArrayList<BaseMove>();
    private ArrayList<String> dexList = new ArrayList<String>();
    private ArrayList<BasePokemon> pokedexList = new ArrayList<BasePokemon>();
    private PokemonCollection collection, datacollection;
    private String datapath;
    public DataClass() {
        this.datapath = "src/main/resources/static/data/";
    }
    
    public ArrayList<BasePokemon> getCollection() {
        return this.collection.getCollection();
    }
    
    public PokemonCollection getDataCollection() {
        return this.datacollection;
    }
    
    public ArrayList<String> getDexList() {
        return this.dexList;
    }
    
    public ArrayList<BasePokemon> getPokedexList() {
        return this.pokedexList;
    }
    
    public void initBasePokemon() 
        throws IOException {
        File file = new File(this.datapath + "Pokestats.txt"); 
        Scanner sc = new Scanner(file); 
        
        ArrayList<BasePokemon> pokeList = new ArrayList<BasePokemon>();
        String name,type1;
        String type2 = "";
        int dexnum,instancenum;
        int basehp,baseatk,basedef,basespatk,basespdef,basespeed;
        boolean secondType = false;
        for (int i=0;i<973;i++) {
            name = sc.next();
            while (!sc.hasNextInt())
                name += " " + sc.next();
            dexnum = Integer.parseInt(sc.next());
            basehp = Integer.parseInt(sc.next());
            baseatk = Integer.parseInt(sc.next());
            basedef = Integer.parseInt(sc.next());
            basespatk = Integer.parseInt(sc.next());
            basespdef = Integer.parseInt(sc.next());
            type1 = sc.next();
            if (!sc.hasNextInt()) {
                type2 = sc.next();
                secondType = true;
            }
            basespeed = Integer.parseInt(sc.next());
            instancenum = Integer.parseInt(sc.next());
            if (secondType) {
                BasePokemon newPoke = new BasePokemon(name,dexnum,instancenum,basehp,baseatk,basedef,basespatk,basespdef,basespeed,type1,type2);
                pokeList.add(newPoke);
            }
            else {
                BasePokemon newPoke = new BasePokemon(name,dexnum,instancenum,basehp,baseatk,basedef,basespatk,basespdef,basespeed,type1);
                pokeList.add(newPoke);
            }
            secondType = false;
        } 
        this.collection = new PokemonCollection();
        
        for (BasePokemon pokemon : pokeList) {
            this.collection.addPokemon(pokemon);
        }
        
        ArrayList<Integer>dexNumList = new ArrayList<Integer>();
        for (BasePokemon pokemon : pokeList) {
            if (!(dexNumList.contains(pokemon.getDexNum()))) {
                dexNumList.add(pokemon.getDexNum());
                this.pokedexList.add(pokemon);
            }
            if (pokemon.getDexNum() >= 721)
                break;
        }
        
        
        this.initBasePokemonData();
        
    }
    
    public void initBasePokemonData() 
        throws IOException
    {
        File file = new File(this.datapath + "PokemonData.txt"); 
        Scanner sc = new Scanner(file); 
        this.datacollection = new PokemonCollection();
        
        String dataname;
        int dataid,dexnum,throwaway3,throwaway4,throwaway5;
        double height, weight;
        boolean isalt,ismega,canchange;
        
        ArrayList<BasePokemon> pokeList = new ArrayList<BasePokemon>();
        ArrayList<String> nameList = new ArrayList<String>();
        pokeList = this.collection.getCollection();
        
        for (BasePokemon pokemon : pokeList) {
            nameList.add(pokemon.getName().toLowerCase());
        }
        
        for (int i=0;i<964;i++) {
            dataid = Integer.parseInt(sc.next());
            dataname = "";
            while (!sc.hasNextInt())
                dataname += sc.next();
            dexnum = Integer.parseInt(sc.next());
            height = (Double.parseDouble(sc.next()))/10;
            weight = (Double.parseDouble(sc.next()))/10;
            throwaway3 = Integer.parseInt(sc.next());
            throwaway4 = Integer.parseInt(sc.next());
            throwaway5 = Integer.parseInt(sc.next());
            
            if (dataid > 10000) {isalt = true;}
            else {isalt = false;}
            
            if (dataname.contains("mega") && isalt) {ismega = true;}
            else {ismega = false;}
            
            if (isalt && ((dataid == 10026) || (dataid == 10117) || ismega)) {canchange = true;}
            else {canchange = false;}
            
            if (nameList.contains(dataname)) {
                BasePokemon pokemon = this.collection.getPokemon(nameList.indexOf(dataname));
                if (pokemon.getDexNum() == dexnum) {
                    pokemon.addData(dataname,dataid,height,weight,isalt,ismega,canchange);
                    this.datacollection.addPokemon(pokemon);
                }
            }
            else {
                
                //special cases
                
                if (dataname.equals("nidoran-f")) {
                    this.collection.getPokemon(nameList.indexOf("nidoran-female")).addData(dataname,dataid,height,weight,isalt,ismega,canchange);
                    this.datacollection.addPokemon(this.collection.getPokemon(nameList.indexOf("nidoran-female")));
                }
                if (dataname.equals("nidoran-m")) {
                    this.collection.getPokemon(nameList.indexOf("nidoran-male")).addData(dataname,dataid,height,weight,isalt,ismega,canchange);
                    this.datacollection.addPokemon(this.collection.getPokemon(nameList.indexOf("nidoran-male")));
                }
                if (dataname.equals("farfetchd")) {
                    this.collection.getPokemon(nameList.indexOf("farfetch'd")).addData(dataname,dataid,height,weight,isalt,ismega,canchange);
                    this.datacollection.addPokemon(this.collection.getPokemon(nameList.indexOf("farfetch'd")));
                }
                if (dataname.equals("mr-mime")) {
                    this.collection.getPokemon(nameList.indexOf("mr._mime")).addData(dataname,dataid,height,weight,isalt,ismega,canchange);
                    this.datacollection.addPokemon(this.collection.getPokemon(nameList.indexOf("mr._mime")));
                }
                if (dataname.equals("deoxys-normal")) {
                    this.collection.getPokemon(nameList.indexOf("deoxys")).addData(dataname,dataid,height,weight,isalt,ismega,canchange);
                    this.datacollection.addPokemon(this.collection.getPokemon(nameList.indexOf("deoxys")));
                }
                if (dataname.equals("burmy")) {
                    this.collection.getPokemon(nameList.indexOf("burmy-plant")).addData(dataname,dataid,height,weight,isalt,ismega,canchange);
                    this.datacollection.addPokemon(this.collection.getPokemon(nameList.indexOf("burmy-plant")));
                }
                if (dataname.equals("cherrim")) {
                    this.collection.getPokemon(nameList.indexOf("cherrim-overcast")).addData(dataname,dataid,height,weight,isalt,ismega,canchange);
                    this.datacollection.addPokemon(this.collection.getPokemon(nameList.indexOf("cherrim-overcast")));
                }
                if (dataname.equals("shellos")) {
                    this.collection.getPokemon(nameList.indexOf("shellos-east")).addData(dataname,dataid,height,weight,isalt,ismega,canchange);
                    this.datacollection.addPokemon(this.collection.getPokemon(nameList.indexOf("shellos-east")));
                }
                if (dataname.equals("gastrodon")) {
                    this.collection.getPokemon(nameList.indexOf("gastrodon-east")).addData(dataname,dataid,height,weight,isalt,ismega,canchange);
                    this.datacollection.addPokemon(this.collection.getPokemon(nameList.indexOf("gastrodon-east")));
                }
                if (dataname.equals("mime-jr")) {
                    this.collection.getPokemon(nameList.indexOf("mime_jr.")).addData(dataname,dataid,height,weight,isalt,ismega,canchange);
                    this.datacollection.addPokemon(this.collection.getPokemon(nameList.indexOf("mime_jr.")));
                }
                if (dataname.equals("unfezant")) {
                    this.collection.getPokemon(nameList.indexOf("unfezant-male")).addData(dataname,dataid,height,weight,isalt,ismega,canchange);
                    this.datacollection.addPokemon(this.collection.getPokemon(nameList.indexOf("unfezant-male")));
                }
                if (dataname.equals("basculin-red-striped")) {
                    this.collection.getPokemon(nameList.indexOf("basculin-red")).addData(dataname,dataid,height,weight,isalt,ismega,canchange);
                    this.datacollection.addPokemon(this.collection.getPokemon(nameList.indexOf("basculin-red")));
                }
                if (dataname.equals("basculin-blue-striped")) {
                    this.collection.getPokemon(nameList.indexOf("basculin-blue")).addData(dataname,dataid,height,weight,isalt,ismega,canchange);
                    this.datacollection.addPokemon(this.collection.getPokemon(nameList.indexOf("basculin-blue")));
                }
                if (dataname.equals("darmanitan-standard")) {
                    this.collection.getPokemon(nameList.indexOf("darmanitan")).addData(dataname,dataid,height,weight,isalt,ismega,canchange);
                    this.datacollection.addPokemon(this.collection.getPokemon(nameList.indexOf("darmanitan")));
                }
                if (dataname.equals("deerling")) {
                    this.collection.getPokemon(nameList.indexOf("deerling-spring")).addData(dataname,dataid,height,weight,isalt,ismega,canchange);
                    this.datacollection.addPokemon(this.collection.getPokemon(nameList.indexOf("deerling-spring")));
                }
                if (dataname.equals("sawsbuck")) {
                    this.collection.getPokemon(nameList.indexOf("sawsbuck-spring")).addData(dataname,dataid,height,weight,isalt,ismega,canchange);
                    this.datacollection.addPokemon(this.collection.getPokemon(nameList.indexOf("sawsbuck-spring")));
                }
                if (dataname.equals("jellicent")) {
                    this.collection.getPokemon(nameList.indexOf("jellicent-male")).addData(dataname,dataid,height,weight,isalt,ismega,canchange);
                    this.datacollection.addPokemon(this.collection.getPokemon(nameList.indexOf("jellicent-male")));
                }
                if (dataname.equals("frillish")) {
                    this.collection.getPokemon(nameList.indexOf("frillish-male")).addData(dataname,dataid,height,weight,isalt,ismega,canchange);
                    this.datacollection.addPokemon(this.collection.getPokemon(nameList.indexOf("frillish-male")));
                }
                if (dataname.equals("tornadus-incarnate")) {
                    this.collection.getPokemon(nameList.indexOf("tornadus")).addData(dataname,dataid,height,weight,isalt,ismega,canchange);
                    this.datacollection.addPokemon(this.collection.getPokemon(nameList.indexOf("tornadus")));
                }
                if (dataname.equals("thundurus-incarnate")) {
                    this.collection.getPokemon(nameList.indexOf("thundurus")).addData(dataname,dataid,height,weight,isalt,ismega,canchange);
                    this.datacollection.addPokemon(this.collection.getPokemon(nameList.indexOf("thundurus")));
                }
                if (dataname.equals("landorus-incarnate")) {
                    this.collection.getPokemon(nameList.indexOf("landorus")).addData(dataname,dataid,height,weight,isalt,ismega,canchange);
                    this.datacollection.addPokemon(this.collection.getPokemon(nameList.indexOf("landorus")));
                }
                if (dataname.equals("keldeo-ordinary")) {
                    this.collection.getPokemon(nameList.indexOf("keldeo")).addData(dataname,dataid,height,weight,isalt,ismega,canchange);
                    this.datacollection.addPokemon(this.collection.getPokemon(nameList.indexOf("keldeo")));
                }
                if (dataname.equals("pyroar")) {
                    this.collection.getPokemon(nameList.indexOf("pyroar-male")).addData(dataname,dataid,height,weight,isalt,ismega,canchange);
                    this.datacollection.addPokemon(this.collection.getPokemon(nameList.indexOf("pyroar-male")));
                }
                if (dataname.equals("type-null")) {
                    this.collection.getPokemon(nameList.indexOf("type_null")).addData(dataname,dataid,height,weight,isalt,ismega,canchange);
                    this.datacollection.addPokemon(this.collection.getPokemon(nameList.indexOf("type_null")));
                }
                if (dataname.equals("tapu-koko")) {
                    this.collection.getPokemon(nameList.indexOf("tapu_koko")).addData(dataname,dataid,height,weight,isalt,ismega,canchange);
                    this.datacollection.addPokemon(this.collection.getPokemon(nameList.indexOf("tapu_koko")));
                }
                if (dataname.equals("tapu-lele")) {
                    this.collection.getPokemon(nameList.indexOf("tapu_lele")).addData(dataname,dataid,height,weight,isalt,ismega,canchange);
                    this.datacollection.addPokemon(this.collection.getPokemon(nameList.indexOf("tapu_lele")));
                }
                if (dataname.equals("tapu-bulu")) {
                    this.collection.getPokemon(nameList.indexOf("tapu_bulu")).addData(dataname,dataid,height,weight,isalt,ismega,canchange);
                    this.datacollection.addPokemon(this.collection.getPokemon(nameList.indexOf("tapu_bulu")));
                }
                if (dataname.equals("tapu-fini")) {
                    this.collection.getPokemon(nameList.indexOf("tapu_fini")).addData(dataname,dataid,height,weight,isalt,ismega,canchange);
                    this.datacollection.addPokemon(this.collection.getPokemon(nameList.indexOf("tapu_fini")));
                }
                if (dataname.equals("charizard-mega-x")) {
                    this.collection.getPokemon(nameList.indexOf("charizard-mega_x")).addData(dataname,dataid,height,weight,isalt,ismega,canchange);
                    this.datacollection.addPokemon(this.collection.getPokemon(nameList.indexOf("charizard-mega_x")));
                }
                if (dataname.equals("charizard-mega-y")) {
                    this.collection.getPokemon(nameList.indexOf("charizard-mega_y")).addData(dataname,dataid,height,weight,isalt,ismega,canchange);
                    this.datacollection.addPokemon(this.collection.getPokemon(nameList.indexOf("charizard-mega_y")));
                }
                if (dataname.equals("mewtwo-mega-x")) {
                    this.collection.getPokemon(nameList.indexOf("mewtwo-mega_x")).addData(dataname,dataid,height,weight,isalt,ismega,canchange);
                    this.datacollection.addPokemon(this.collection.getPokemon(nameList.indexOf("mewtwo-mega_y")));
                }
                if (dataname.equals("mewtwo-mega-y")) {
                    this.collection.getPokemon(nameList.indexOf("mewtwo-mega_y")).addData(dataname,dataid,height,weight,isalt,ismega,canchange);
                    this.datacollection.addPokemon(this.collection.getPokemon(nameList.indexOf("mewtwo-mega_y")));
                }
                if (dataname.equals("zygarde-10")) {
                    this.collection.getPokemon(nameList.indexOf("zygarde-10%")).addData(dataname,dataid,height,weight,isalt,ismega,canchange);
                    this.datacollection.addPokemon(this.collection.getPokemon(nameList.indexOf("zygarde-10%")));
                }
                if (dataname.equals("zygarde-50")) {
                    this.collection.getPokemon(nameList.indexOf("zygarde")).addData(dataname,dataid,height,weight,isalt,ismega,canchange);
                    this.datacollection.addPokemon(this.collection.getPokemon(nameList.indexOf("zygarde")));
                }
                if (dataname.equals("oricorio")) {
                    this.collection.getPokemon(nameList.indexOf("oricorio-baile")).addData(dataname,dataid,height,weight,isalt,ismega,canchange);
                    this.datacollection.addPokemon(this.collection.getPokemon(nameList.indexOf("oricorio-baile")));
                }
                if (dataname.equals("oricorio-pau")) {
                    this.collection.getPokemon(nameList.indexOf("oricorio-pa'u")).addData(dataname,dataid,height,weight,isalt,ismega,canchange);
                    this.datacollection.addPokemon(this.collection.getPokemon(nameList.indexOf("oricorio-pa'u")));
                }
                if (dataname.equals("necrozma-dusk")) {
                    this.collection.getPokemon(nameList.indexOf("necrozma-dusk_mane")).addData(dataname,dataid,height,weight,isalt,ismega,canchange);
                    this.datacollection.addPokemon(this.collection.getPokemon(nameList.indexOf("necrozma-dusk_mane")));
                }
                if (dataname.equals("necrozma-dawn")) {
                    this.collection.getPokemon(nameList.indexOf("necrozma-dawn_wings")).addData(dataname,dataid,height,weight,isalt,ismega,canchange);
                    this.datacollection.addPokemon(this.collection.getPokemon(nameList.indexOf("necrozma-dawn_wings")));
                }
            }    
        }
    }
    
    public void initBaseMove() 
        throws IOException
    {
        File file = new File(this.datapath + "Moves.txt"); 
        Scanner sc = new Scanner(file); 
        ArrayList<BaseMove> moveList = new ArrayList<BaseMove>();
        for (int i=0;i<728;i++) {
            int moveid = Integer.parseInt(sc.next());
            String movename = sc.next();
            sc.next();
            int movetype = Integer.parseInt(sc.next());
            int power = Integer.parseInt(sc.next());
            int pp = Integer.parseInt(sc.next());
            double accuracy = Double.parseDouble(sc.next())/100;
            int priority = Integer.parseInt(sc.next());
            int targetid = Integer.parseInt(sc.next());
            int damageid = Integer.parseInt(sc.next());
            int effectid = Integer.parseInt(sc.next());
            double effectchance = Double.parseDouble(sc.next())/100;
            sc.next();
            sc.next();
            sc.next();
            
            BaseMove newmove = new BaseMove(moveid,movename,movetype,power,pp,accuracy,priority,targetid,damageid,effectid,effectchance);
            moveList.add(newmove);
        }
        this.moveList = moveList;
    }
    
    public void initBaseAbility() 
        throws IOException
    {
        File file = new File(this.datapath + "Abilities.txt"); 
        Scanner sc = new Scanner(file); 
        Scanner sc2 = new Scanner(file);
        ArrayList<BaseAbility> abilityList = new ArrayList<BaseAbility>();
        for (int i=0;i<233;i++) {
            int abilityid = Integer.parseInt(sc.next());
            String abilityname = sc.next();
            BaseAbility newability = new BaseAbility(abilityid,abilityname);
            abilityList.add(newability);
        }
        this.abilityList = abilityList;
        
    }
    
    public void initDexDescriptions()
        throws IOException
    {
        File file = new File(this.datapath + "DexDescriptions.txt");
        ArrayList<String> dexList = new ArrayList<String>(973);
        BufferedReader br = new BufferedReader(new FileReader(file));
        for (int i=0;i<721;i++) {
            String str = "";
            String line = "";
            boolean first = true;
            
            while (true) {
                line = br.readLine();
                if (line.contains("\"") && !first) {
                    str += line;
                    break;
                }
                str += line + " ";
                first = false;
            }
            str = str.replace("\"","");
            dexList.add(str);
        }
        for (int i=0;i<252;i++) {
        	dexList.add("No Pok&eacute;dex information available for this Pok&eacute;mon.");
        }
        this.dexList = dexList;
    }
    
    
    public void merge() 
        throws IOException
    {
        //creating a list of Possible Abilities within each individual BasePokemon object
        
        File file = new File(this.datapath + "PossibleAbilities.txt"); 
        Scanner sc = new Scanner(file); 
        ArrayList<BasePokemon> dataList = new ArrayList<BasePokemon>();
        ArrayList<Integer> dataidList = new ArrayList<Integer>();
        dataList = this.datacollection.getCollection();
        for (BasePokemon pokemon : dataList)
            dataidList.add(pokemon.getDataID());
        int datacollectionsize = this.datacollection.getSize();
        int dataid,abilityid;
        for (int i=0;i<2167;i++) {
            dataid = Integer.parseInt(sc.next());
            abilityid = Integer.parseInt(sc.next());
            sc.next();
            sc.next();
            if (dataidList.contains(dataid)) {
                dataList.get(dataidList.indexOf(dataid)).addAbilities(this.abilityList.get(abilityid-1));
            }
        }
        
        //creating a list of Possible Moves within each individual BasePokemon object
        
        file = new File(this.datapath + "PossibleMoves.txt"); 
        sc = new Scanner(file); 
        
        int moveid;
        for (int i=0;i<465817;i++) {
            dataid = Integer.parseInt(sc.next());
            sc.next();
            moveid = Integer.parseInt(sc.next());
            sc.next();
            sc.next();
            if (dataidList.contains(dataid)) {
                dataList.get(dataidList.indexOf(dataid)).addMoves(this.moveList.get(moveid-1));
            }
        }
    }
    
}