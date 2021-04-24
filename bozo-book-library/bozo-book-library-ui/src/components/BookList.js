import React, { useState, useEffect, useContext } from 'react'
import Book from './Book';
import './Book-Catalogue.css';
import './nicepage.css'
import SearchField from "react-search-field";
import { CurrentUserContext } from './CurrentUserContext';

var resultObject = null;


const BookList = () => {
    const [searchQuery, setSearchQuery] = useState("");
    const [searchResponse, setSearchResponse] = useState("");
    const [searchResultTotalCount, setSearchResulTotalCount] = useState(0);
    const [currentPage, setCurrentPage] = useState(1);

    useEffect(() => {
        queryService();
    }, [currentPage, searchQuery]);

    const queryService = () => {
        fetch('http://localhost:8080/graphql', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                query: `
                {search ( keyword: "`+ searchQuery + `", page: ` + currentPage + `) {
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
                console.log("Received the response...");
                setSearchResponse("received");
                setSearchResulTotalCount(resultObject.search.totalItems);
            });
    }

    const onSearch = (value, event) => {
        console.log(value);
        setSearchQuery(value);
        setCurrentPage(1);
        queryService();
    };

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

    return (
        <div className="BookList">
            <h2>Browse {user}</h2>
            <SearchField
                placeholder="Search..."
                classNames="test-class"
                onSearchClick={onSearch}
                onEnter={onSearch}
            />

            {resultObject &&
                <div>
                    <section class="u-align-center u-clearfix u-grey-5 u-section-1" id="carousel_ed60">
                        <div class="u-clearfix u-sheet u-sheet-1">
                            {console.log("Result Object: " + resultObject)}
                            {resultObject.search.items &&
                                <div>
                                    {console.log("Result Items: " + resultObject.search.items)}
                                    <p>Results of {searchQuery} {currentPage * 10}-{(currentPage * 10) + 10} / {resultObject.search.totalItems} books </p>
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
                                        {resultObject.search.items.map(function (volume) {
                                            var thumbnail = ""
                                            if (volume.volumeInfo.imageLinks != null) {
                                                thumbnail = volume.volumeInfo.imageLinks.thumbnail;
                                            }
                                            return (
                                                <div>
                                                    <Book bookId={volume.id} bookname={volume.volumeInfo.title} bookdescription={volume.volumeInfo.description} author={volume.volumeInfo.authors} imageLink={thumbnail} />
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

export default BookList;