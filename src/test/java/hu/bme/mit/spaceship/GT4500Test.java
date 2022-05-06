package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore mockTSPrimary;
  private TorpedoStore mockTSSecondary;

  @BeforeEach
  public void init(){
    this.ship = new GT4500();
    mockTSPrimary = mock(TorpedoStore.class);
    mockTSSecondary = mock(TorpedoStore.class);
    this.ship.setPrimaryTorpedoStore(mockTSPrimary);
    this.ship.setSecondaryTorpedoStore(mockTSSecondary);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(mockTSPrimary.fire(1)).thenReturn(true);
    when(mockTSPrimary.isEmpty()).thenReturn(false);
    when(mockTSSecondary.isEmpty()).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);

    // Verify
    verify(mockTSPrimary, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(mockTSPrimary.fire(1)).thenReturn(true);
    when(mockTSPrimary.isEmpty()).thenReturn(false);
    when(mockTSSecondary.fire(1)).thenReturn(true);
    when(mockTSSecondary.isEmpty()).thenReturn(false);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);

    // Verify
    verify(mockTSPrimary, times(1)).fire(1);
    verify(mockTSSecondary, times(1)).fire(1);
  }

}
