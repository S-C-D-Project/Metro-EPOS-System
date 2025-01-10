package Controllers;

import Models.DataBaseHandler;
import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BranchTest {

    private Branch branch;

    @BeforeEach
    void setUp() {
        branch = new Branch(1, "Test Address", "123456789", 10, true);
    }

    @AfterEach
    void tearDown() {
        branch = null;
    }

    @Test
    void testGetId() {
        assertEquals(1, branch.getId());
    }

    @Test
    void testSetId() {
        branch.setId(2);
        assertEquals(2, branch.getId());
    }

    @Test
    void testGetAddress() {
        assertEquals("Test Address", branch.getAddress());
    }

    @Test
    void testSetAddress() {
        branch.setAddress("New Address");
        assertEquals("New Address", branch.getAddress());
    }

    @Test
    void testGetPhoneNumber() {
        assertEquals("123456789", branch.getPhoneNumber());
    }

    @Test
    void testSetPhoneNumber() {
        branch.setPhoneNumber("987654321");
        assertEquals("987654321", branch.getPhoneNumber());
    }

    @Test
    void testGetNumberOfEmployees() {
        assertEquals(10, branch.getNumberOfEmployees());
    }

    @Test
    void testSetNumberOfEmployees() {
        branch.setNumberOfEmployees(15);
        assertEquals(15, branch.getNumberOfEmployees());
    }

    @Test
    void testIsActive() {
        assertTrue(branch.isActive());
    }

    @Test
    void testSetActive() {
        branch.setActive(false);
        assertFalse(branch.isActive());
    }

    @Test
    void testConstructorWithId_FoundInDatabase() {
        try (MockedStatic<DataBaseHandler> mockedStatic = mockStatic(DataBaseHandler.class)) {
            Branch mockBranch = new Branch(2, "Database Address", "111222333", 5, true);
            mockedStatic.when(() -> DataBaseHandler.getBranch(2)).thenReturn(mockBranch);

            Branch testBranch = new Branch(2);

            assertEquals(2, testBranch.getId());
            assertEquals("Database Address", testBranch.getAddress());
            assertEquals("111222333", testBranch.getPhoneNumber());
            assertEquals(5, testBranch.getNumberOfEmployees());
            assertTrue(testBranch.isActive());

            mockedStatic.verify(() -> DataBaseHandler.getBranch(2), times(1));
        }
    }

    @Test
    void testConstructorWithId_NotFoundInDatabase() {
        try (MockedStatic<DataBaseHandler> mockedStatic = mockStatic(DataBaseHandler.class)) {
            mockedStatic.when(() -> DataBaseHandler.getBranch(3)).thenReturn(null);

            Branch testBranch = new Branch(3);

            assertEquals(1, testBranch.getId()); // Default values
            assertEquals("700b", testBranch.getAddress());
            assertEquals("030394124", testBranch.getPhoneNumber());
            assertEquals(32, testBranch.getNumberOfEmployees());
            assertFalse(testBranch.isActive());

            mockedStatic.verify(() -> DataBaseHandler.getBranch(3), times(1));
        }
    }
}
