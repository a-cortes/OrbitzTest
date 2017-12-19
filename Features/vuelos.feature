@tag
Feature: buscar vuelo y/o hotel

Background:
	
	

  @Paquete
  Scenario: Buscar vuelo y hotel
    Given User selecciona packages
    And Selecciona origen y destino
    And Selecciona departing y returning
    And Numero de adultos
    When Presiono Search
    And Reservo primer hotel
    And Reservo primer vuelo ida
    And Reservo primer vuelo vuelta
    When Continuar reserva
    Then Llenar datos de viajero
    
  @Vuelo
  Scenario Outline: Buscar vuelo
    Given User selecciona vuelo
    And Selecciona "<origen>" y "<destino>" de vuelo
    And Selecciona departing y returning de vuelo
    When Presiono Search vuelo
    And Reservo primer vuelo ida sin paquete
    And Reservo primer vuelo vuelta sin paquete
    When Continuar reserva de vuelo
    Then Llenar datos de viajero en vuelo
     Examples: 
      | origen  | destino |
      | Monterrey | Mexico |
      | Monterrey | Atlanta |
    
    
    
  
