import React, { useState, useEffect } from 'react';
import "./home.css"
import axios from 'axios';
import { Grid,Box,Card, CardActions, CardContent, CardMedia, Button, Typography } from '@mui/material';
import { Link } from 'react-router-dom';



function Home() {

  const [movies, setMovies] = useState([]);

  useEffect(() => {
    axios.get('http://localhost:8080/movies/all', {
  headers: {
    'Access-Control-Allow-Origin': '*'
  }})
      .then(response => {
        setMovies(response.data);
      })
      .catch(error => {
        console.log(error);
      });
  }, []);
  
return (
    <Grid container spacing={2} justifyContent="center">
    {movies.map(movie => (
      <Grid item xs={12} sm={6} md={4} key={movie.id}>
      <Card sx={{ margin: '1rem' }}>
        <CardMedia
          component="img"
          height="400"
          image={movie.imageUrl}
          alt={movie.name}
        />
        <CardContent>
          <Typography gutterBottom variant="h5" component="div">
            {movie.name}
          </Typography>
          <Typography variant="body2" color="text.secondary">
            {movie.category} - {movie.type}
          </Typography>
        </CardContent>
        <CardActions>
          <Button component={Link} to={`/booking/${movie.id}`} size="large" sx={{color:"orange"}}>
            Book Now
          </Button>
        </CardActions>
      </Card>
     </Grid>
    ))}
    </Grid>
  
);
};

export default Home;

