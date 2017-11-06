
package Trabalho1.Cargo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Vinicius Cerqueira Nascimento
 * @author Marina Ribeiro Kodama
 * @author Marco Aurelio Geremias
 */
public class CargoDAO{
    
    private HashMap<Integer, Cargo> cacheCargos = new HashMap<>();
    private final String fileName = "cargo.dat"; 
    
    public CargoDAO(){
        this.load();
    }
    
    public void put(Cargo cargo){
        cacheCargos.put(cargo.getCodigo(), cargo);    
        this.persist();
    }
    
    public Cargo get(Integer matricula){
        return cacheCargos.get(matricula);
    }
    
    public Collection<Cargo> getList(){
        return cacheCargos.values();
    }

    public void remove(Cargo cargo) {
        this.cacheCargos.remove(cargo.getCodigo());
        this.persist();
    }
    
    private void persist(){
        
        try {
            FileOutputStream fout = new FileOutputStream(this.fileName);
            ObjectOutputStream oo = new ObjectOutputStream(fout);
            
            oo.writeObject(cacheCargos);
            
            oo.flush();
            fout.flush();
            
            oo.close();
            fout.close();
         
        } 
        catch (FileNotFoundException ex) {
            Logger.getLogger(CargoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (IOException ex) {
            Logger.getLogger(CargoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void load(){
        
        try {
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream oo = new ObjectInputStream(fis);
            
            cacheCargos = (HashMap<Integer, Cargo>)oo.readObject();
            
            oo.close();
            fis.close();
        } 
        catch (FileNotFoundException ex) {
            Logger.getLogger(CargoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (IOException ex) {
            Logger.getLogger(CargoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (ClassNotFoundException ex) {
            Logger.getLogger(CargoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
}
