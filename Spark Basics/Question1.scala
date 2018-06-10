val business_data = sc.textFile("business.csv")
var filtered_file = business_data.filter(line => line.contains("Stanford"))
val split_data = filtered_file.map(line => (line.split("::")(0),1))
val review_data = sc.textFile("review.csv")
val review_split_data = review_data.map(line => (line.split("::")(2),line.split("::")(1),line.split("::")(3)))
var shift_split_data = split_data.map(t => (t._1,t._2))
var shift_review_data = review_split_data.map(t => (t._1,t._2+" - "+t._3))
val join_data = shift_split_data.join(shift_review_data)
var data = join_data.map(t=>(t._2))
var result = data.map(t=>(t._2))
result.collect().foreach(a => println(a))