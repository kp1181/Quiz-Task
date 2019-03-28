import React from 'react';
import { Paper, Grid } from '@material-ui/core';
import IconButton from "@material-ui/core/IconButton";
import ClearIcon from "@material-ui/icons/Clear";


class ResultPaper {

  render(){
    return (
      <Paper>
        <Grid container>
          <Grid item xs={8}>
            <p>{this.props.row.query}</p>
            <p> =  {this.props.row.query}</p>
          </Grid>
          <Grid item xs={4}>
              <IconButton >
                  <ClearIcon style={btnStyle} />
              </IconButton>  
          </Grid>
        </Grid>
      </Paper>
    );
  };
}


const btnStyle = {
  backgroundColor: "gray",
  color: "white",
  border: "none",
  borderRadius: "50%",
  cursor: "pointer",
  width: 20,
  height: 20
};


export default ResultPaper;