import React, { Component } from 'react'
import Book from './Book';
import './Book-Catalogue.css';
import SearchField from "react-search-field";

var resultObject = null;
var queryString = `query Search($keyword: String) {
    search(keyword: $keyword) {
        totalItems
        items {
          volumeInfo {
            title
            authors
            description
            imageLinks {
              smallThumbnail
              thumbnail
            }
          }
        }
    }
}`

class BookList extends Component {
    constructor() {
        super()
        this.state = {
            searchQuery: "",
            searchResult: ""
        }
    }

    onSearch = (value, event) => {
        console.log(value);
        this.setState({
            searchQuery: value,
        });

        fetch('http://localhost:8080/graphql', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                query: `
                {search ( keyword: "`+value+`") {
                      totalItems
                      items {
                        volumeInfo {
                          title
                          authors
                          description
                          imageLinks {
                            smallThumbnail
                            thumbnail
                          }
                        }
                      }
                    }
                  }`
            })
        })
            .then(res => res.json())
            .then(res => {
                resultObject = res.data
                this.setState(
                    {
                        searchResult:"received"
                    }
                )
            });
    }

    render() {
        var items = [];
        if(resultObject !=null ) {
            items = resultObject.search.items;
        }
        return (
            <div className="BookList">
                <SearchField
                    placeholder="Search..."
                    classNames="test-class"
                    onSearchClick={this.onSearch}
                    onEnter={this.onSearch}
                />
                {resultObject != null &&
                    <p>Search Results for {this.state.searchQuery} returned {resultObject.search.totalItems} books </p>
                }    
                <div class="u-list u-repeater u-list-1 u-align-center">
                    {items.map(function(volume){
                        var thumbnail = ""
                        if(volume.volumeInfo.imageLinks != null) {
                            thumbnail = volume.volumeInfo.imageLinks.thumbnail;
                        }
                            
                        return (
                        <div>
                            <Book bookname={volume.volumeInfo.title} bookdescription={volume.volumeInfo.description} author={volume.volumeInfo.authors} imageLink={thumbnail}/>    
                        </div>)
                    })}
                </div>
            </div>

        );
    }
}




export default BookList;