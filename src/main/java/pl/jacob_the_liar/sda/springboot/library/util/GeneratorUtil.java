package pl.jacob_the_liar.sda.springboot.library.util;


/**
 * @author: Jakub O.  [https://github.com/JacobTheLiar]
 * @date : 2019-10-20 11:05
 * *
 * @className: GeneratorUtil
 * *
 * *
 ******************************************************/
public class GeneratorUtil{
    
    private static int genId = 0;
    
    
    public static int getNewId(){
        return genId++;
    }
    
    
}
