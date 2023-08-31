//package com.moviebooking.service;
//
//import com.moviebooking.entity.*;
//import com.moviebooking.exception.CommonException;
//import com.moviebooking.exception.MovieNotFoundException;
//import com.moviebooking.repository.MovieRepository;
//import com.moviebooking.repository.SeatRepository;
//import com.moviebooking.repository.TicketRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import static org.assertj.core.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@ExtendWith(SpringExtension.class)
//class TicketServiceTests {
//    @InjectMocks
//    private TicketServiceImpl ticketService;
//    @Mock
//    private SeatRepository seatRepository;
//    @Mock
//    private MovieRepository movieRepository;
//    @Mock
//    private SequenceGenerator sequenceGenerator;
//    @Mock
//    private TicketRepository ticketRepository;
//    private Ticket ticket;
//    private Movie movie;
//    private MovieId movieId;
//    private Customer customer;
//
//    private Seat seat1;
//
//    @BeforeEach
//    void setUp(){
//        movieId = new MovieId("movieName", "theatreName");
//       // movie = new Movie(movieId, 1, 100.00, 100, 1, "Available");
//        seat1 = new Seat(1, 2, "Balcony", SeatStatus.Available, 100.0, movie);
//        //Seat seat2 = new Seat(1, 2, "Balcony", SeatStatus.Available, 100.0, movie);
//        List<Seat> seats = List.of(seat1);
//        customer = new Customer("userFirst", "userLast", "abc@gmail.com", 1, "user123", "pass123", "pass123", 1111111111, "user");
//        ticket = new Ticket(1,"movieName","theatreName",100.00,1,seats,customer);
//    }
//
//    @Test
//    void addMovie_Success(){
//        when(movieRepository.findById(movieId)).thenReturn(Optional.of(movie));
//        when(seatRepository.findBySeatNumberAndMovieId(2,1)).thenReturn(seat1);
//        ticketService.addTicket(customer,ticket);
//        verify(movieRepository,times(1)).save(movie);
//    }
//
//    @Test
//    void addMovie_CommonException_SeatBooked(){
//        seat1.setSeatStatus(SeatStatus.Booked);
//        when(movieRepository.findById(movieId)).thenReturn(Optional.of(movie));
//        when(seatRepository.findBySeatNumberAndMovieId(2,1)).thenReturn(seat1);
//        CommonException e = assertThrows(CommonException.class,()->{ticketService.addTicket(customer,ticket);});
//        assertEquals("2 is already booked.Please select a new seat",e.getMessage());
//    }
//
//    @Test
//    void addMovie_CommonException(){
//        when(movieRepository.findById(movieId)).thenReturn(Optional.of(movie));
//        movie.setNoOfTicketsSold(movie.getNoOfTicketsAllotted());
//        CommonException e = assertThrows(CommonException.class,()->{ticketService.addTicket(customer,ticket);});
//        assertEquals("Tickets not available",e.getMessage());
//    }
//    @Test
//    void addMovie_SizeCommonException(){
//        when(movieRepository.findById(movieId)).thenReturn(Optional.of(movie));
//        ticket.setNoOfTickets(2);
//        CommonException e = assertThrows(CommonException.class,()->{ticketService.addTicket(customer,ticket);});
//        assertEquals("No.of seats selected should be equal size of seats list",e.getMessage());
//    }
//
//    @Test
//    void addMovie_MovieNotFoundException(){
//        when(movieRepository.findById(movieId)).thenReturn(Optional.empty());
//        MovieNotFoundException e = assertThrows(MovieNotFoundException.class,()->{ticketService.addTicket(customer,ticket);});
//        assertEquals("Movie not found",e.getMessage());
//    }
//}
