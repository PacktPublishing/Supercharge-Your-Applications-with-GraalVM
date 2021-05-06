import React, { useState, useEffect, useContext } from 'react'
import Book from './Book';
import './Book-Catalogue.css';
import './nicepage.css'
import SearchField from "react-search-field";
import { CurrentUserContext } from './CurrentUserContext';
var resultObject = null;

const Library = (props) => {
    const [searchQuery, setSearchQuery] = useState("");
    const [searchResponse, setSearchResponse] = useState("");
    const [searchResultTotalCount, setSearchResulTotalCount] = useState(0);
    const [currentPage, setCurrentPage] = useState(1);

    useEffect(() => {
        getLibraryBooks();
    }, [currentPage, searchQuery]);

    const BOOK_INFO_SERVICE_URL = process.env.REACT_APP_BOOK_INFO_SERVICE_URL
    const BOOK_LIB_SERVICE_URL= process.env.REACT_APP_BOOK_LIB_SERVICE_URL + '/booklib'

    const getBooks = (bookids) => {
        var bookidsJson = JSON.stringify(bookids);
        fetch(BOOK_INFO_SERVICE_URL+'/graphql', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                query: `
            {getBooks ( bookids: `+ bookidsJson + `) {
                  totalItems
                  items {
                    id
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
                console.log("Received the response... " + res);
                setSearchResponse("received ");
                setSearchResulTotalCount(resultObject.getBooks.totalItems);
            });
    }


    const getLibraryBooks = () => {
        fetch(BOOK_LIB_SERVICE_URL+'/get/'+{user})
            .then(res => res.json())
            .then(data => {
                console.log(data);
                var string = JSON.stringify(data);
                var objects = JSON.parse(string);
                var bookids = [];
                objects.forEach(function (key, index) {
                    console.log(key.bookID);
                    bookids[index] = key.bookID
                })
                getBooks(bookids);
            });
    }

    const previousPage = () => {
        if (currentPage > 1) {
            setCurrentPage(currentPage - 1);
        }
    };

    const nextPage = () => {
        var totalPages = searchResultTotalCount / 10;
        if (currentPage < totalPages) {
            setCurrentPage(currentPage + 1);
        }
    };
    const { user } = useContext(CurrentUserContext);

    getLibraryBooks()

    return (
        <div className="BookList">
            {resultObject &&
                <div>
                    <section class="u-align-center u-clearfix u-grey-5 u-section-1" id="carousel_ed60">
                        <div class="u-clearfix u-sheet u-sheet-1">
                            {resultObject.getBooks.items &&
                                <div>
                                    <table >
                                        <tr>
                                            <td>
                                                <button name="Prev" class="u-border-2 u-border-grey-25 u-btn u-btn-rectangle u-button-style u-none u-text-body-color u-btn-1" onClick={previousPage}>Previous Page</button>
                                            </td>
                                            <td></td>
                                            <td>
                                                <button name="Next" class="u-border-2 u-border-grey-25 u-btn u-btn-rectangle u-button-style u-none u-text-body-color u-btn-1" onClick={nextPage}>Next Page </button>
                                            </td>
                                        </tr>
                                    </table>



                                    <div class="u-list u-repeater u-list-1">
                                        {resultObject.getBooks.items.map(function (volume) {
                                            var thumbnail = ""
                                            if (volume.volumeInfo.imageLinks != null) {
                                                thumbnail = volume.volumeInfo.imageLinks.thumbnail;
                                            }
                                            return (
                                                <div>
                                                    <Book bookId={volume.id} bookname={volume.volumeInfo.title} bookdescription={volume.volumeInfo.description} author={volume.volumeInfo.authors} imageLink={thumbnail}  removeButton={true}/>
                                                </div>)
                                        })}
                                    </div>

                                </div>
                            }
                        </div>
                    </section>
                </div>
            }
        </div>

    );
}

export default Library;