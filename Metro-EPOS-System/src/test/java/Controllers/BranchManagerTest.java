package Controllers;

import Models.DataBaseHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BranchManagerTest {
    private BranchManager branchManager;

    @BeforeEach
    void setUp() {
        branchManager = new BranchManager();
    }

    @Test
    void testAddEmployee() {
        try (MockedStatic<DataBaseHandler> mockedStatic = mockStatic(DataBaseHandler.class)) {
            mockedStatic.when(() -> DataBaseHandler.addEmployee("Test Case", "Test@gmail.com", 0, 1, "Cashier")).thenReturn(1);

            int value = branchManager.addEmployee("Test Case", "Test@gmail.com", 0, 1, "Cashier");
            assertTrue(value > 0);

            mockedStatic.verify(() -> DataBaseHandler.addEmployee("Test Case", "Test@gmail.com", 0, 1, "Cashier"), times(1));
        }
    }

    @Test
    void testUpdateEmployee_NullEmployee() {
        Employee e = null;
        boolean result = branchManager.updateEmployee(e);
        assertFalse(result);
    }
//    @Test
//    void testConstructorWithFullDetails() {
//        BranchManager manager = new BranchManager(
//                "John Doe", "password123", "john.doe@example.com", 101, 5, 50000,
//                "2023-01-01", "2023-12-31", true, false, "BranchManager"
//        );
//
//        assertEquals("John Doe", manager.getName());
//        assertEquals("password123", manager.getPassword());
//        assertEquals("john.doe@example.com", manager.getEmail());
//        assertEquals(50000, manager.getSalary());
//        assertEquals("2023-01-01", manager.getJoiningDate());
//        assertEquals("2023-12-31", manager.getLeavingDate());
//        assertTrue(manager.isActive());
//        assertFalse(manager.isFirstTime());
//        assertEquals("BranchManager", manager.getRole());
//    }

    @Test
    void testDefaultConstructor() {
        BranchManager manager = new BranchManager();

        // Assuming the default constructor initializes the parent Employee class fields to default values
        assertNull(manager.getName());
        assertNull(manager.getPassword());
        assertNull(manager.getEmail());
        assertEquals(0, manager.getSalary());
        assertNull(manager.getJoiningDate());
        assertNull(manager.getLeavingDate());
        assertFalse(manager.isActive());
        assertFalse(manager.isFirstTime());
        assertNull(manager.getRole());
    }

    @Test
    void testConstructorWithPartialDetails() {
        BranchManager manager = new BranchManager(
                "Jane Doe", "securePass", "jane.doe@example.com", 102, 3, 60000,
                true, "BranchManager", "123-456-7890"
        );

        assertEquals("Jane Doe", manager.getName());
        assertEquals("securePass", manager.getPassword());
        assertEquals("jane.doe@example.com", manager.getEmail());
        assertEquals(60000, manager.getSalary());
        assertTrue(manager.isActive());
        assertEquals("BranchManager", manager.getRole());
        assertEquals("123-456-7890", manager.getPhoneNumber());
    }

//    @Test
//    void testUpdateEmployee_ValidEmployee() {
//        try (MockedStatic<DataBaseHandler> mockedStatic = mockStatic(DataBaseHandler.class)) {
//            Branch branch = new Branch(1, "700b", "030394124",32,false);
//            Employee e = new Cashier("Test", "password", "test@gmail.com", 1, branch.getId(), 5000,
//                    "2023-01-01", "", true, false, "Cashier");
//
//            mockedStatic.when(() -> DataBaseHandler.updateEmployee(1, "Test", "test@gmail.com", branch.getId(), 5000,
//                    true, "Cashier", null)).thenReturn(true);
//
//            boolean result = branchManager.updateEmployee(e);
//            assertFalse(result);
//
//            mockedStatic.verify(() -> DataBaseHandler.updateEmployee(1, "Test", "test@gmail.com", branch.getId(), 5000,
//                    true, "Cashier", null), times(1));
//        }
//    }


    @Test
    void testGetEmployeesByBranch_ValidBranch() {
        try (MockedStatic<DataBaseHandler> mockedStatic = mockStatic(DataBaseHandler.class)) {
            ArrayList<String> mockList = new ArrayList<>();
            mockList.add("Employee1");
            mockList.add("Employee2");

            mockedStatic.when(() -> DataBaseHandler.getEmployeesByBranch(1)).thenReturn(mockList);

            ArrayList<String> employees = branchManager.getEmployeesByBranch(1);
            assertNotNull(employees);
            assertEquals(2, employees.size());
            assertEquals("Employee1", employees.get(0));

            mockedStatic.verify(() -> DataBaseHandler.getEmployeesByBranch(1), times(1));
        }
    }

    @Test
    void testGetEmployeesByBranch_InvalidBranch() {
        try (MockedStatic<DataBaseHandler> mockedStatic = mockStatic(DataBaseHandler.class)) {
            mockedStatic.when(() -> DataBaseHandler.getEmployeesByBranch(-1)).thenReturn(null);

            ArrayList<String> employees = branchManager.getEmployeesByBranch(-1);
            assertNull(employees);

            mockedStatic.verify(() -> DataBaseHandler.getEmployeesByBranch(-1), times(1));
        }
    }
}
