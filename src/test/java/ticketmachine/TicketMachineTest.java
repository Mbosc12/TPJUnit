package ticketmachine;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TicketMachineTest {
	private static final int PRICE = 50; // Une constante

	private TicketMachine machine; // l'objet à tester

	@Before
	public void setUp() {
		machine = new TicketMachine(PRICE); // On initialise l'objet à tester
	}

	@Test
	// On vérifie que le prix affiché correspond au paramètre passé lors de l'initialisation
	// S1 : le prix affiché correspond à l’initialisation
	public void priceIsCorrectlyInitialized() {
		// Paramètres : message si erreur, valeur attendue, valeur réelle
		assertEquals("Initialisation incorrecte du prix", PRICE, machine.getPrice());
	}

	@Test
	// S2 : la balance change quand on insère de l’argent
	public void insertMoneyChangesBalance() {
		machine.insertMoney(10);
		machine.insertMoney(20);
		assertEquals("La balance n'est pas correctement mise à jour", 10 + 20, machine.getBalance()); // Les montants ont été correctement additionnés               
	}
        
        @Test
        // S3 : on n'imprime pas le ticket si le montant inséré est insuffisant
        public void MontantInsererInsuffisant()  {
                assertFalse("Il y a pas assez d'argent inséré", machine.printTicket());
        }
        
        @Test
        // S4 : on imprime le ticket si le montant inséré est suffisant
        public void MontantInserSuffisant() {
                machine.insertMoney(50);
                assertTrue("Montant inséré insuffisant, ticket non imprimé", machine.printTicket());
        }
        
        @Test
        // S5 : Quand on imprime un ticket la balance est décrémentée du prix du ticket
        public void DecrementationBalance() {
                machine.insertMoney(50);
                machine.printTicket();
                assertEquals("La balance n'a pas été décrémentée", machine.getPrice() - 50, machine.getBalance());
        }
        
        @Test
        // S6 : Le montant collecté est mis à jour quand on imprime un ticket ( pas avant )
        public void MAJTotal() {
            machine.insertMoney(50);
            machine.insertMoney(20);
            assertEquals(0, machine.getTotal());
            machine.printTicket();
            assertEquals("Le montant n'est pas mis à jour", machine.getPrice() , machine.getTotal());
        }
        
        @Test
        // S7 : rend correctement la monnaie
        public void RenduMonnaie() {
            machine.insertMoney(machine.getPrice() + 50);
            assertEquals((machine.getPrice() + 50), machine.getBalance());
            machine.printTicket();
            assertEquals(50, machine.getBalance());
            machine.refund();
            assertEquals("Mauvais rendu monnaie ", 0, machine.getBalance());
            
            
        }
        
        @Test
        // S8: remet la balance à zero 
        public void ResetBalance() {
            machine.insertMoney(machine.getPrice() + 50);
            assertEquals((machine.getPrice() + 50), machine.getBalance());
            machine.printTicket();
            machine.refund();
            assertEquals("La balance n'a pas été reset", machine.getBalance(), 0);
        }
        
        @Test
        // S9: pas de montant inserer negatif 
        public void InsertionNegative() {
            machine.insertMoney(-10);
            assertEquals("Le montant n'est pas passé", 0, machine.getBalance());
        }
        
        @Test 
        // S10 : pas de machine qui délivrer des tickets avec prix négatifs
        public  void MachineOP() {
            try  {
                TicketMachine machineX = new TicketMachine(-10);
                fail("La machine a eu un prix négatif");
            }catch (IllegalArgumentException e) {
                
            }
            
        }
}  
   
