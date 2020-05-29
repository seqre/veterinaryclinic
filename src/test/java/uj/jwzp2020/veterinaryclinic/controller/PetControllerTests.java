package uj.jwzp2020.veterinaryclinic.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.server.ResponseStatusException;
import uj.jwzp2020.veterinaryclinic.model.client.Address;
import uj.jwzp2020.veterinaryclinic.model.client.Client;
import uj.jwzp2020.veterinaryclinic.model.client.Gender;
import uj.jwzp2020.veterinaryclinic.model.client.dto.AddressDTO;
import uj.jwzp2020.veterinaryclinic.model.client.dto.ClientResponseDTO;
import uj.jwzp2020.veterinaryclinic.model.client.dto.GenderDTO;
import uj.jwzp2020.veterinaryclinic.model.pet.Pet;
import uj.jwzp2020.veterinaryclinic.model.pet.Species;
import uj.jwzp2020.veterinaryclinic.model.pet.dto.PetResponseDTO;
import uj.jwzp2020.veterinaryclinic.model.pet.dto.SpeciesDTO;
import uj.jwzp2020.veterinaryclinic.service.ClientService;
import uj.jwzp2020.veterinaryclinic.service.PetService;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(MockitoExtension.class)
public class PetControllerTests {

    public static final int YEAR = 2020;
    public static final int MONTH = 6;
    public static final int DAY = 5;

    private final Pet a = new Pet(1L, "a", 1L, Species.DOG, LocalDate.of(2020, 2, 1), null);
    private final PetResponseDTO aResponseDTO = new PetResponseDTO(1L, "a", 1L, SpeciesDTO.DOG, LocalDate.of(2020, 2, 2), null);
    private final Pet b = new Pet(2L, "b", 2L, Species.CAT, LocalDate.of(2020, 2, 1), null);
    private final PetResponseDTO bResponseDTO = new PetResponseDTO(2L, "b", 2L, SpeciesDTO.CAT, LocalDate.of(2020, 2, 2), null);

    private final Client aClient = new Client(1L, "a", "a", LocalDate.of(YEAR, MONTH, DAY), Gender.MALE, new Address(), "e", null);
    private final ClientResponseDTO aClientResponseDTO = new ClientResponseDTO(1L, "a", "a", LocalDate.of(YEAR, MONTH, DAY), GenderDTO.MALE, new AddressDTO(), "e", null);

    private MockMvc mockMvc;

    @Mock
    private PetService petService;

    @Mock
    private ClientService clientService;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PetController petController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(petController)
                .build();
    }

    @Nested
    class GetPets {

        @Test
        public void returnsEmptyListForEmptyDatabase() throws Exception {
            given(petService.getPets()).willReturn(Collections.emptyList());

            MockHttpServletResponse response = mockMvc.perform(
                    get("/pets")
                            .accept(MediaType.APPLICATION_JSON))
                    .andReturn()
                    .getResponse();

            assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
            assertThat(response.getContentLength()).isEqualTo(0);
        }

        @Test
        public void returnsOnePersonInListForOnePersonInDatabase() throws Exception {
            given(petService.getPets()).willReturn(List.of(a));
            given(modelMapper.map(a, PetResponseDTO.class)).willReturn(aResponseDTO);

            MockHttpServletResponse response = mockMvc.perform(
                    get("/pets")
                            .accept(MediaType.APPLICATION_JSON))
                    .andReturn()
                    .getResponse();

            assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
            assertThat(response.getContentAsString()).contains("1", "a");
        }

        @Test
        public void returnsPeopleInListForPeopleInDatabase() throws Exception {
            given(petService.getPets()).willReturn(List.of(a, b));
            given(modelMapper.map(a, PetResponseDTO.class)).willReturn(aResponseDTO);
            given(modelMapper.map(b, PetResponseDTO.class)).willReturn(bResponseDTO);

            MockHttpServletResponse response = mockMvc.perform(
                    get("/pets")
                            .accept(MediaType.APPLICATION_JSON))
                    .andReturn()
                    .getResponse();

            assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
            assertThat(response.getContentAsString()).contains("1", "a", "2", "b");
        }
    }

    @Nested
    class GetPetById {

        @Test
        public void throwsExceptionOnNegativeId() throws Exception {
            given(petService.getPetById(-1L)).willThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));

            MockHttpServletResponse response = mockMvc.perform(
                    get("/pets/-1")
                            .accept(MediaType.APPLICATION_JSON))
                    .andReturn()
                    .getResponse();

            assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
        }

        @Test
        public void throwsExceptionOnNonExistentId() throws Exception {
            given(petService.getPetById(1L)).willThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));

            MockHttpServletResponse response = mockMvc.perform(
                    get("/pets/1")
                            .accept(MediaType.APPLICATION_JSON))
                    .andReturn()
                    .getResponse();

            assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
        }

        @Test
        public void returnsOnExistentId() throws Exception {
            given(petService.getPetById(1L)).willReturn(a);
            given(modelMapper.map(a, PetResponseDTO.class)).willReturn(aResponseDTO);

            MockHttpServletResponse response = mockMvc.perform(
                    get("/pets/1")
                            .accept(MediaType.APPLICATION_JSON))
                    .andReturn()
                    .getResponse();

            assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
            assertThat(response.getContentAsString()).contains("1", "a");
        }
    }

    @Nested
    class GetPetOwnerByPetId {

        @Test
        public void throwsExceptionOnNegativeId() throws Exception {
            given(petService.getPetById(-1L)).willThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));

            MockHttpServletResponse response = mockMvc.perform(
                    get("/pets/-1")
                            .accept(MediaType.APPLICATION_JSON))
                    .andReturn()
                    .getResponse();

            assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
        }

        @Test
        public void throwsExceptionOnNonExistentId() throws Exception {
            given(petService.getPetById(1L)).willThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));

            MockHttpServletResponse response = mockMvc.perform(
                    get("/pets/1")
                            .accept(MediaType.APPLICATION_JSON))
                    .andReturn()
                    .getResponse();

            assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
        }

        @Test
        public void returnsOnExistentId() throws Exception {
            given(petService.getPetById(1L)).willReturn(a);
            given(clientService.getClientById(1L)).willReturn(aClient);
            given(modelMapper.map(aClient, ClientResponseDTO.class)).willReturn(aClientResponseDTO);

            MockHttpServletResponse response = mockMvc.perform(
                    get("/pets/1")
                            .accept(MediaType.APPLICATION_JSON))
                    .andReturn()
                    .getResponse();

            assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
            assertThat(response.getContentAsString()).contains("1", "a", "male", "e");
        }

    }
}
