package fr.digi.cda2024.services;

import static org.mockito.Mockito.*;
import org.mockito.Mock;
import org.mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.*;

import java.util.List;

public class QuerysMenuTest {
    @Mock
    private EntityManagerFactory emfMock;
    @Mock
    private EntityManager emMock;
    @Mock
    private TypedQuery<String> queryMock;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        QuerysMenuCopy.setEntityManagerFactory(emfMock);
        when(emfMock.createEntityManager()).thenReturn(emMock);
    }

    @Test
    public void testGetFilmographieActeur() {
        // Données des mocks
        String acteurNom = "Jabbabab";
        List<String> expectedFilmographie = List.of("Jabbabab 1", "Jabbabab 2 : le retour");

        // Simuler le comportement de la méthode createQuery et query.getResultList()
        when(emMock.createQuery(anyString(), eq(String.class))).thenReturn(queryMock);
        when(queryMock.setParameter(anyString(), anyString())).thenReturn(queryMock);
        when(queryMock.getResultList()).thenReturn(expectedFilmographie);

        // Appeler la méthode à tester
        List<String> filmographie = QuerysMenuCopy.getFilmographieActeur(acteurNom);

        // Vérifier le résultat
        assertEquals(expectedFilmographie, filmographie);

        // Vérifier que les méthodes appropriées ont été appelées sur le mock
        verify(emMock).createQuery(anyString(), eq(String.class));
        verify(queryMock).setParameter("nomActeur", acteurNom);
        verify(queryMock).getResultList();
    }

    // Test de getCastingFilm
    @Test
    public void testGetCastingFilm() {
        String filmNom = "Jabbabab 1";
        List<String> expectedCasting = List.of("Henry Cavill", "Jabbabab");

        when(emMock.createQuery(anyString(), eq(String.class))).thenReturn(queryMock);
        when(queryMock.setParameter(anyString(), anyString())).thenReturn(queryMock);
        when(queryMock.getResultList()).thenReturn(expectedCasting);

        List<String> casting = QuerysMenuCopy.getCastingFilm(filmNom);

        assertEquals(expectedCasting, casting);
        verify(emMock).createQuery(anyString(), eq(String.class));
        verify(queryMock).setParameter("nomFilm", filmNom);
        verify(queryMock).getResultList();
    }

    // Test de getFilmsEntreDeuxAnnees
    @Test
    public void testGetFilmsEntreDeuxAnnees() {
        int anneeDebut = 2000;
        int anneeFin = 2010;
        List<String> expectedFilms = List.of("Jabbabab 1", "Jabbabab 2 : le retour");

        when(emMock.createQuery(anyString(), eq(String.class))).thenReturn(queryMock);
        when(queryMock.setParameter(eq("anneeDebut"), any(String.class))).thenReturn(queryMock);
        when(queryMock.setParameter(eq("anneeFin"), any(String.class))).thenReturn(queryMock);
        when(queryMock.getResultList()).thenReturn(expectedFilms);

        List<String> films = QuerysMenuCopy.getFilmsEntreDeuxAnnees(anneeDebut, anneeFin);

        assertEquals(expectedFilms, films);
        verify(emMock).createQuery(anyString(), eq(String.class));
        verify(queryMock).setParameter(eq("anneeDebut"), any(String.class));
        verify(queryMock).setParameter(eq("anneeFin"), any(String.class));
        verify(queryMock).getResultList();
    }

    // Test de getFilmsCommunsPourDeuxActeurs
    @Test
    public void testGetFilmsCommunsPourDeuxActeurs() {
        String acteurNom1 = "Jabbabab";
        String acteurNom2 = "Henry Cavill";
        List<String> expectedFilmsCommuns = List.of("Jabbabab 1", "Jabbabab 2 : le retour");

        when(emMock.createQuery(anyString(), eq(String.class))).thenReturn(queryMock);
        when(queryMock.setParameter(eq("acteur1"), anyString())).thenReturn(queryMock);
        when(queryMock.setParameter(eq("acteur2"), anyString())).thenReturn(queryMock);
        when(queryMock.getResultList()).thenReturn(expectedFilmsCommuns);

        List<String> filmsCommuns = QuerysMenuCopy.getFilmsCommunsPourDeuxActeurs(acteurNom1, acteurNom2);

        assertEquals(expectedFilmsCommuns, filmsCommuns);
        verify(emMock).createQuery(anyString(), eq(String.class));
        verify(queryMock).setParameter("acteur1", acteurNom1);
        verify(queryMock).setParameter("acteur2", acteurNom2);
        verify(queryMock).getResultList();
    }

    // Test de getActeursCommunsPourDeuxFilms
    @Test
    public void testGetActeursCommunsPourDeuxFilms() {
        String film1 = "Jabbabab 1";
        String film2 = "Jabbabab 2 : le retour";
        List<String> expectedActeursCommuns = List.of("Henry Cavill", "Jabbabab");

        when(emMock.createQuery(anyString(), eq(String.class))).thenReturn(queryMock);
        when(queryMock.setParameter(eq("film1"), anyString())).thenReturn(queryMock);
        when(queryMock.setParameter(eq("film2"), anyString())).thenReturn(queryMock);
        when(queryMock.getResultList()).thenReturn(expectedActeursCommuns);

        List<String> acteursCommuns = QuerysMenuCopy.getActeursCommunsPourDeuxFilms(film1, film2);

        assertEquals(expectedActeursCommuns, acteursCommuns);
        verify(emMock).createQuery(anyString(), eq(String.class));
        verify(queryMock).setParameter("film1", film1);
        verify(queryMock).setParameter("film2", film2);
        verify(queryMock).getResultList();
    }

    // Test de getFilmsEntreDeuxAnneesAvecActeur
    @Test
    public void testGetFilmsEntreDeuxAnneesAvecActeur() {
        int anneeDebut = 2000;
        int anneeFin = 2010;
        String acteurNom = "Jabbabab";
        List<String> expectedFilmsEntreAvecActeur = List.of("Jabbabab 1", "Jabbabab 2 : le retour");

        when(emMock.createQuery(anyString(), eq(String.class))).thenReturn(queryMock);
        when(queryMock.setParameter(eq("anneeDebut"), any(String.class))).thenReturn(queryMock);
        when(queryMock.setParameter(eq("anneeFin"), any(String.class))).thenReturn(queryMock);
        when(queryMock.setParameter(eq("acteurNom"), anyString())).thenReturn(queryMock);
        when(queryMock.getResultList()).thenReturn(expectedFilmsEntreAvecActeur);

        List<String> filmsEntreAvecActeur = QuerysMenuCopy.getFilmsEntreDeuxAnneesAvecActeur(anneeDebut, anneeFin, acteurNom);

        assertEquals(expectedFilmsEntreAvecActeur, filmsEntreAvecActeur);
        verify(emMock).createQuery(anyString(), eq(String.class));
        verify(queryMock).setParameter(eq("anneeDebut"), any(String.class));
        verify(queryMock).setParameter(eq("anneeFin"), any(String.class));
        verify(queryMock).setParameter("acteurNom", acteurNom);
        verify(queryMock).getResultList();
    }

}
